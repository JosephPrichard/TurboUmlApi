package com.turbouml.controllers;

import com.turbouml.exceptions.AccessDeniedException;
import com.turbouml.services.ClassService;
import com.turbouml.utils.Session;
import com.turbouml.utils.ResponseUtils;
import com.turbouml.dto.Serializer;
import com.turbouml.dto.enums.Access;
import com.turbouml.dto.models.UMLClass;
import com.turbouml.utils.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.util.List;

import static com.turbouml.dto.models.UMLDiagram.MAX_X;
import static com.turbouml.dto.models.UMLDiagram.MAX_Y;

/**
 * Provides endpoint for CRUD operations on UMLClasses
 */
@CrossOrigin
@Validated
@RestController
public class ClassController
{
    private final ClassService classService;

    @Autowired
    public ClassController(@Qualifier("ClassService") ClassService classService)
    {
        this.classService = classService;
    }

    /**
     * Create a new UML class in the database
     *
     * @param name name of the class, should be between 4 and 30 characters
     * @param projectId id of the project the field will belong to
     * @param access PUBLIC, PRIVATE, PROTECTED, PACKAGE_PRIVATE, INTERNAL
     * @param xPos x position of the class on the diagram canvas 0 - 2000
     * @param yPos y position of the class on the diagram canvas 0 - 2000
     * @return new class as a json or failure
     */
    @ResponseBody
    @RequestMapping(value="/classes/create", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> createClass(
        @Size(min = 4, max = 30) @RequestParam String name,
        @RequestParam String projectId,
        @Valid @RequestParam Access access,
        @Valid @Min(0) @Max(MAX_X) @RequestParam int xPos,
        @Valid @Min(0) @Max(MAX_Y) @RequestParam int yPos
    )
    {
        String userId = Session.userIdContext();
        var newClass = new UMLClass();
        newClass.setClassId(ID.generate());
        newClass.setContentName(name);
        newClass.setAccess(access);
        newClass.setStereotypes(new String[]{});
        newClass.setXPos(xPos);
        newClass.setYPos(yPos);
        newClass.setProjectId(projectId);
        try {
            classService.saveClass(userId, newClass);
            return new ResponseEntity<>(
                Serializer.serialize(newClass),
                HttpStatus.OK
            );
        } catch(DataAccessException | IOException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    /**
     * Retrieve a single UML class
     *
     * @param classId id of the class to retrieve
     * @return class as a json or failure
     */
    @ResponseBody
    @RequestMapping(value="/classes/getById", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> findClass(
        @RequestParam String classId
    )
    {
        String userId = Session.userIdContext();
        try {
            return new ResponseEntity<>(
                Serializer.serialize(
                    classService.retrieveClass(userId, classId)
                ),
                HttpStatus.OK
            );
        } catch(DataAccessException | IOException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Retrieve all UML classes for a project
     *
     * @param projectId id of the project
     * @return classes in a json array or failure
     */
    @ResponseBody
    @RequestMapping(value="/classes/forProject", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> findClassForProject(@RequestParam String projectId)
    {
        String userId = Session.userIdContext();
        try {
            return new ResponseEntity<>(
                Serializer.serialize(
                    classService.retrieveAllClasses(userId, projectId)
                ),
                HttpStatus.OK
            );
        } catch(DataAccessException | IOException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Change the stereotypes for the class
     *
     * @param classId id of the class to update
     * @param stereotype the stereotypes as a string array (allows multiple parameters)
     * @return success or failure
     */
    @ResponseBody
    @RequestMapping(value="/classes/updateStereotype", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> updateClassStereotype(
        @RequestParam String classId,
        @RequestParam String[] stereotype
    )
    {
        String userId = Session.userIdContext();
        try {
            classService.updateClassStereotype(userId, classId, stereotype);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(DataAccessException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Rename the UML class
     *
     * @param classId id of the class to update
     * @param name new name for the UML class, should be between 4 and 30 characters
     * @return success or failure
     */
    @ResponseBody
    @RequestMapping(value="/classes/rename", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> renameClass(
        @RequestParam String classId,
        @Size(min = 4, max = 30) @RequestParam String name
    )
    {
        String userId = Session.userIdContext();
        try {
            classService.renameClass(userId, classId, name);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(DataAccessException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Move the UML class on the diagram canvas
     *
     * @param classId id of the class to update
     * @param xPos new x position of the class on the diagram canvas 0 - 2000
     * @param yPos new y position of the class on the diagram canvas 0 - 2000
     * @return success or failure
     */
    @ResponseBody
    @RequestMapping(value="/classes/move", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> moveClass(
        @RequestParam(value="class_id") String classId,
        @Valid @Min(0) @Max(MAX_X) @RequestParam int xPos,
        @Valid @Min(0) @Max(MAX_Y) @RequestParam int yPos
    )
    {
        String userId = Session.userIdContext();
        try {
            classService.moveClass(userId, classId, xPos, yPos);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(DataAccessException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete any number of UML classes
     *
     * @param classId the classIds to delete as a string array (allows multiple parameters)
     * @return success or failure
     */
    @ResponseBody
    @RequestMapping(value="/classes/delete", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> deleteClass(
        @RequestParam List<String> classId
    )
    {
        String userId = Session.userIdContext();
        try {
            if(classId.size() == 1) {
                classService.deleteClass(userId, classId.get(0));
            } else {
                classService.deleteClasses(userId, classId);
            }
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

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDenied(AccessDeniedException ex)
    {
        return new ResponseEntity<>(
            ResponseUtils.getResponse(ex.getMessage()),
            HttpStatus.UNAUTHORIZED
        );
    }
}
