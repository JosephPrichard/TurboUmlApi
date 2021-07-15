package com.turbouml.controllers;

import com.turbouml.exceptions.ResourceScopeException;
import com.turbouml.services.RelationshipService;
import com.turbouml.dto.Serializer;
import com.turbouml.dto.enums.RelationshipType;
import com.turbouml.dto.models.UMLRelationship;
import com.turbouml.exceptions.AccessDeniedException;
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

/**
 * Provides an endpoint for CRUD operations on UML relationships
 */
@CrossOrigin
@Validated
@RestController
public class RelationshipController
{
    private final RelationshipService relationshipService;

    @Autowired
    public RelationshipController(@Qualifier("RelationshipService") RelationshipService relationshipService) {
        this.relationshipService = relationshipService;
    }

    /**
     * Create a UML relationship for a project
     *
     * @param classIdFrom id of the class the relationship is from
     * @param classIdTo id of the class the relationship is to
     * @param label text label of the relationship
     * @param type INHERITANCE, REALIZATION, ASSOCIATION, AGGREGATION, COMPOSITION, DEPENDENCY
     * @return new relationship as a json or failure
     */
    @ResponseBody
    @RequestMapping(value="/relationships/create", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> createRelationship(
        @RequestParam String classIdFrom,
        @RequestParam String classIdTo,
        @RequestParam(required = false) String label,
        @Valid @RequestParam RelationshipType type
    )
    {
        String userId = Session.userIdContext();
        var newRelationship = new UMLRelationship();
        newRelationship.setRelationshipId(ID.generate());
        newRelationship.setType(type);
        newRelationship.setClassIdTo(classIdTo);
        newRelationship.setClassIdFrom(classIdFrom);
        newRelationship.setLabel(label);
        try {
            relationshipService.saveRelationship(userId, newRelationship);
            return new ResponseEntity<>(
                Serializer.serialize(newRelationship),
                HttpStatus.OK
            );
        } catch(DataAccessException | IOException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    /**
     * Retrieve all relationship for the projects
     *
     * @param projectId id of the project
     * @return relationships in a json array or failure
     */
    @ResponseBody
    @RequestMapping(value="/relationships/forProject", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> findRelationshipsForProject(@RequestParam String projectId)
    {
        String userId = Session.userIdContext();
        try {
            return new ResponseEntity<>(
                Serializer.serialize(
                    relationshipService.retrieveAllRelationships(userId, projectId)
                ),
                HttpStatus.OK
            );
        } catch(DataAccessException | IOException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Rename (relabel) a relationship
     *
     * @param relationshipId id of the relationship
     * @param label new label text
     * @return success or failure
     */
    @ResponseBody
    @RequestMapping(value="/relationships/rename", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> relabelRelationship(
        @RequestParam String relationshipId,
        @RequestParam(required = false) String label
    )
    {
        String userId = Session.userIdContext();
        try {
            relationshipService.renameRelationship(userId, relationshipId, label);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(DataAccessException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete a single relationship
     * @param relationshipId of the relationship id
     * @return success or failure
     */
    @ResponseBody
    @RequestMapping(value="/relationships/delete", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> deleteRelationship(@RequestParam String relationshipId)
    {
        String userId = Session.userIdContext();
        try {
            relationshipService.deleteRelationship(userId, relationshipId);
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
}
