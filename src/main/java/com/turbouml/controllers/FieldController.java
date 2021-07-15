package com.turbouml.controllers;

import com.turbouml.dto.Serializer;
import com.turbouml.dto.enums.Access;
import com.turbouml.dto.models.UMLField;
import com.turbouml.exceptions.AccessDeniedException;
import com.turbouml.exceptions.InvalidArgumentsException;
import com.turbouml.exceptions.ResourceScopeException;
import com.turbouml.services.FieldService;
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
 * Provides an endpoint for CRUD operations on UMLFields
 */
@CrossOrigin
@Validated
@RestController
public class FieldController
{
    private final FieldService fieldService;

    @Autowired
    public FieldController(@Qualifier("FieldService") FieldService fieldService) {
        this.fieldService = fieldService;
    }

    /**
     * Create a new UML field, ordered at the end of all other fields in the class
     *
     * @param name name of the UML field
     * @param dataType type of the UML field (optional) (defaults to null)
     * @param classId id of the class the field will belong to
     * @param access PUBLIC, PRIVATE, PROTECTED, PACKAGE_PRIVATE, INTERNAL
     * @param isStatic true or false
     * @return new field as a json or failure
     */
    @ResponseBody
    @RequestMapping(value="/fields/create", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> createField(
        @RequestParam String name,
        @RequestParam(required = false) String dataType,
        @RequestParam String classId,
        @Valid @RequestParam Access access,
        @RequestParam boolean isStatic
    )
    {
        String userId = Session.userIdContext();
        var newField = new UMLField();
        newField.setFieldId(ID.generate());
        newField.setContentName(name);
        newField.setDataType(dataType);
        newField.setClassId(classId);
        newField.setAccess(access);
        newField.setStatic(isStatic);
        try {
            fieldService.saveField(userId, newField);
            return new ResponseEntity<>(
                Serializer.serialize(newField),
                HttpStatus.OK
            );
        } catch(DataAccessException | IOException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    /**
     * Retrieves all fields for a UML class
     *
     * @param classId id of the class
     * @return fields in a json array or failure
     */
    @ResponseBody
    @RequestMapping(value="/fields/forClass", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> findFieldForClass(@RequestParam String classId)
    {
        String userId = Session.userIdContext();
        try {
            return new ResponseEntity<>(
                Serializer.serialize(
                    fieldService.retrieveAllFields(userId, classId)
                ),
                HttpStatus.OK
            );
        } catch(DataAccessException | IOException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Update any number of UML fields
     *
     * @param name new name for the UML field
     * @param dataType new dataType (optional) (defaults to null)
     * @param access PUBLIC, PRIVATE, PROTECTED, PACKAGE_PRIVATE, INTERNAL
     * @param isStatic true or false
     * @param fieldId ids of the field to update as a string array (allows multiple parameters)
     * @return success or failure
     */
    @ResponseBody
    @RequestMapping(value="/fields/update", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> updateField(
        @RequestParam String name,
        @RequestParam(required = false) String dataType,
        @Valid @RequestParam Access access,
        @RequestParam boolean isStatic,
        @RequestParam List<String> fieldId
    )
    {
        String userId = Session.userIdContext();
        try {
            var updatedField = new UMLField();
            updatedField.setContentName(name);
            updatedField.setAccess(access);
            updatedField.setStatic(isStatic);
            updatedField.setDataType(dataType);

            if(fieldId.size() == 1) {
                updatedField.setFieldId(fieldId.get(0));
                fieldService.updateField(userId, updatedField);
            } else {
                fieldService.batchUpdateFields(userId, updatedField, fieldId);
            }

            return new ResponseEntity<>(HttpStatus.OK);
        } catch(DataAccessException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Re-order the UML field in the class
     *
     * @param fieldIdNewOrder the id that has the position the field will be moved to
     * @param fieldIdToMove the id of the field to be re-ordered
     * @return success or failure
     */
    @ResponseBody
    @RequestMapping(value="/fields/move", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> moveField(
        @RequestParam(required = false) String fieldIdNewOrder,
        @RequestParam String fieldIdToMove
    )
    {
        String userId = Session.userIdContext();
        try {
            fieldService.moveField(userId, fieldIdToMove, fieldIdNewOrder);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(DataAccessException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete a single field
     *
     * @param fieldId id of field to delete
     * @return success or failure
     */
    @ResponseBody
    @RequestMapping(value="/fields/delete", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> deleteField(@RequestParam String fieldId)
    {
        String userId = Session.userIdContext();
        try {
            fieldService.deleteField(userId, fieldId);
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
