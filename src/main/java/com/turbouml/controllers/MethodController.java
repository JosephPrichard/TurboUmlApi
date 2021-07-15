package com.turbouml.controllers;

import com.turbouml.dto.Serializer;
import com.turbouml.dto.enums.Access;
import com.turbouml.dto.models.UMLMethod;
import com.turbouml.exceptions.AccessDeniedException;
import com.turbouml.exceptions.InvalidArgumentsException;
import com.turbouml.exceptions.ResourceScopeException;
import com.turbouml.services.MethodService;
import com.turbouml.utils.Session;
import com.turbouml.utils.ID;
import com.turbouml.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * Provides an endpoint for CRUD operations on UMLMethods
 */
@CrossOrigin
@Validated
@RestController
public class MethodController
{
    private final MethodService methodService;

    @Autowired
    public MethodController(@Qualifier("MethodService") MethodService methodService)
    {
        this.methodService = methodService;
    }

    /**
     * Create a new UML method, ordered at the end of all other methods in the class
     *
     * @param name name of the UML method
     * @param returnType type of the UML method (optional) (defaults to null)
     * @param classId id of the class the method will belong to
     * @param access PUBLIC, PRIVATE, PROTECTED, PACKAGE_PRIVATE, INTERNAL
     * @param isStatic true or false
     * @param isAbstract true or false
     * @param params parameters of the UML method as a string, can take any format
     *               "param: Type, param1: Type1" is usually adhered to, but not required
     * @return the new method as a json or failure
     */
    @ResponseBody
    @RequestMapping(value="/methods/create", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> createMethod(
        @RequestParam String name,
        @RequestParam(required = false) String returnType,
        @RequestParam String classId,
        @Valid @RequestParam Access access,
        @RequestParam boolean isStatic,
        @RequestParam boolean isAbstract,
        @RequestParam String params
    )
    {
        String userId = Session.userIdContext();
        var newMethod = new UMLMethod();
        newMethod.setMethodId(ID.generate());
        newMethod.setContentName(name);
        newMethod.setReturnType(returnType);
        newMethod.setParams(params);
        newMethod.setAccess(access);
        newMethod.setStatic(isStatic);
        newMethod.setAbstract(isAbstract);
        newMethod.setClassId(classId);
        try {
            methodService.saveMethod(userId, newMethod);
            return new ResponseEntity<>(
                Serializer.serialize(newMethod),
                HttpStatus.OK
            );
        } catch(DataAccessException | IOException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    /**
     * Retrieves all methods for a UML class
     *
     * @param classId id of the class
     * @return methods in a json array or failure
     */
    @ResponseBody
    @RequestMapping(value="/methods/forClass", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> findMethodForClass(String classId)
    {
        String userId = Session.userIdContext();
        try {
            return new ResponseEntity<>(
                Serializer.serialize(
                    methodService.retrieveAllMethods(userId, classId)
                ),
                HttpStatus.OK
            );
        } catch(DataAccessException | IOException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Update any number of UML methods
     *
     * @param name new name for the UML method
     * @param returnType new dataType (optional) (defaults to null)
     * @param access PUBLIC, PRIVATE, PROTECTED, PACKAGE_PRIVATE, INTERNAL
     * @param isStatic true or false
     * @param methodId ids of the methods to update as a string array (allows multiple parameters)
     * @return success or failure
     */
    @ResponseBody
    @RequestMapping(value="/methods/update", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> updateMethod(
        @RequestParam String name,
        @RequestParam(required = false) String returnType,
        @RequestParam String params,
        @Valid @RequestParam Access access,
        @RequestParam boolean isStatic,
        @RequestParam boolean isAbstract,
        @RequestParam List<String> methodId
    )
    {
        String userId = Session.userIdContext();
        try {
            var updatedMethod = new UMLMethod();
            updatedMethod.setContentName(name);
            updatedMethod.setReturnType(returnType);
            updatedMethod.setParams(params);
            updatedMethod.setAccess(access);
            updatedMethod.setAbstract(isAbstract);
            updatedMethod.setStatic(isStatic);

            if(methodId.size() == 1) {
                updatedMethod.setMethodId(methodId.get(0));
                methodService.updateMethod(userId, updatedMethod);
            } else {
                methodService.batchUpdateMethods(userId, updatedMethod, methodId);
            }

            return new ResponseEntity<>(HttpStatus.OK);
        } catch(DataAccessException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Re-order the UML method in the class
     *
     * @param methodIdNewOrder the id that has the position the method will be moved to
     * @param methodIdToMove the id of the method to be re-ordered
     * @return success or failure
     */
    @ResponseBody
    @RequestMapping(value="/methods/move", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> moveMethod(
        @RequestParam(required = false) String methodIdNewOrder,
        @RequestParam String methodIdToMove
    )
    {
        String userId = Session.userIdContext();
        try {
            methodService.moveMethod(userId, methodIdToMove, methodIdNewOrder);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(DataAccessException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete a single method
     *
     * @param methodId id of field to delete
     * @return success or failure
     */
    @ResponseBody
    @RequestMapping(value="/methods/delete", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> deleteMethod(@RequestParam String methodId)
    {
        String userId = Session.userIdContext();
        try {
            methodService.deleteMethod(userId, methodId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(DataAccessException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolation(ConstraintViolationException ex)
    {
        return new ResponseEntity<>(
            ResponseUtils.getResponse(ex.getMessage()),
            HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler({AccessDeniedException.class, ResourceScopeException.class})
    public ResponseEntity<String> handleAccessDenied(Exception ex)
    {
        return new ResponseEntity<>(
            ResponseUtils.getResponse(ex.getMessage()),
            HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler({InvalidArgumentsException.class})
    public ResponseEntity<String> handleInvalidArguments(Exception ex)
    {
        return new ResponseEntity<>(
            ResponseUtils.getResponse(ex.getMessage()),
            HttpStatus.BAD_REQUEST
        );
    }

}
