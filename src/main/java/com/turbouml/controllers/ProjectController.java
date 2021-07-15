package com.turbouml.controllers;

import com.turbouml.dto.Serializer;
import com.turbouml.dto.enums.Lang;
import com.turbouml.dto.models.Project;
import com.turbouml.exceptions.AccessDeniedException;
import com.turbouml.services.ProjectService;
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
import javax.validation.constraints.Size;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Provides an endpoint for CRUD operations on project data
 */
@CrossOrigin
@Validated
@RestController
public class ProjectController
{
    private final ProjectService projectService;

    @Autowired
    public ProjectController(@Qualifier("ProjectService") ProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * Creates a new project
     *
     * @param name name of project, should be between 4 and 30 characters
     * @param lang language of project (doesn't change the rules of the UML diagram)
     * @return new project
     */
    @ResponseBody
    @RequestMapping(value="/projects/create", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> createProject(
        @Size(min = 4, max = 30) @RequestParam String name,
        @Valid @RequestParam Lang lang
    )
    {
        String userId = Session.userIdContext();
        var newProject = new Project();
        newProject.setProjectId(ID.generate());
        newProject.setUserId(Session.userIdContext());
        newProject.setContentName(name);
        newProject.setLang(lang);
        newProject.setTimestamp(
            DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss")
                .format(LocalDateTime.now())
        );
        try {
            projectService.saveProject(userId, newProject);
            return new ResponseEntity<>(
                Serializer.serialize(newProject),
                HttpStatus.OK
            );
        } catch(DataAccessException | IOException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    /**
     * Retrieve a single project
     *
     * @param projectId id of project to get
     * @return project as json or failure
     */
    @ResponseBody
    @RequestMapping(value="/projects/getById", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> findProject(
        @RequestParam String projectId
    )
    {
        String userId = Session.userIdContext();
        try {
            return new ResponseEntity<>(
                Serializer.serialize(
                    projectService.retrieveProject(userId, projectId)
                ),
                HttpStatus.OK
            );
        } catch(DataAccessException | IOException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Retrieve all projects for the logged in user
     *
     * @return projects in a json or failure
     */
    @ResponseBody
    @RequestMapping(value="/projects/getAll", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> findProjectForUser()
    {
        String userId = Session.userIdContext();
        try {
            return new ResponseEntity<>(
                Serializer.serialize(
                    projectService.retrieveAllProjects(userId)
                ),
                HttpStatus.OK
            );
        } catch(DataAccessException | IOException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Rename the project
     *
     * @param projectId id of project to rename
     * @param name name of project, should be between 4 and 30 characters
     * @return success or failure
     */
    @ResponseBody
    @RequestMapping(value="/projects/rename", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> renameProject(
        @RequestParam String projectId,
        @Size(min = 4, max = 30) @RequestParam String name
    )
    {
        String userId = Session.userIdContext();
        try {
            projectService.renameProject(userId, projectId, name);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(DataAccessException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete a single project
     *
     * @param projectId id of project to delete
     * @return success or failure
     */
    @ResponseBody
    @RequestMapping(value="/projects/delete", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> deleteProject(
        @RequestParam String projectId
    )
    {
        String userId = Session.userIdContext();
        try {
            projectService.deleteProject(userId, projectId);
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
            ResponseUtils.getErrorResponse(ex.getMessage()),
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
