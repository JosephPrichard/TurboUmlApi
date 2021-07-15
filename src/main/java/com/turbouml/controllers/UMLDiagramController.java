package com.turbouml.controllers;

import com.turbouml.dto.Serializer;
import com.turbouml.exceptions.AccessDeniedException;
import com.turbouml.services.UMLDiagramService;
import com.turbouml.utils.Session;
import com.turbouml.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Endpoint to retrieve an entire UML diagram by project id
 */
@CrossOrigin
@RestController
public class UMLDiagramController
{
    private final UMLDiagramService umlDiagramService;

    @Autowired
    public UMLDiagramController(@Qualifier("UMLDiagramService") UMLDiagramService umlDiagramService)
    {
        this.umlDiagramService = umlDiagramService;
    }

    /**
     * Retrieve an entire UML diagram by project id
     *
     * @param projectId of the diagram to retrieve
     * @return uml diagram as a json or failure
     */
    @ResponseBody
    @RequestMapping(value="/diagrams/get", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> findDiagramForProject(@RequestParam String projectId)
    {
        String userId = Session.userIdContext();
        try {
            var diagram = umlDiagramService.retrieveDiagram(userId, projectId);
            return new ResponseEntity<>(
                Serializer.serialize(diagram),
                HttpStatus.OK
            );
        } catch(DataAccessException | IOException | SQLException ex) {
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
