package com.turbouml.controllers;

import com.turbouml.dto.Serializer;
import com.turbouml.dto.models.UMLPackage;
import com.turbouml.exceptions.AccessDeniedException;
import com.turbouml.exceptions.InvalidInputException;
import com.turbouml.services.PackageService;
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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.IOException;

import static com.turbouml.dto.models.UMLDiagram.MAX_Y;

/**
 * Provides an endpoint for CRUD operations on UML packages
 */
@CrossOrigin
@Validated
@RestController
public class PackageController
{
    private final PackageService packageService;

    @Autowired
    public PackageController(@Qualifier("PackageService") PackageService packageService) {
        this.packageService = packageService;
    }

    /**
     * Create a new UML package
     *
     * @param xPos x position of the package on the diagram canvas, min 0
     * @param yPos y position of the package on the diagram canvas, min 0
     * @param xDist x length of the package on the canvas,
     *              causes response failure code if it extends outside of the diagram canvas, min 0
     * @param yDist y length of the package on the canvas,
     *              causes response failure code if it extends outside of the diagram canvas, min 0
     * @param name of the package
     * @param projectId id of the project the package belongs to
     * @return the package as a json or failure
     */
    @ResponseBody
    @RequestMapping(value="/packages/create", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> createPackage(
        @Valid @Min(0) @RequestParam int xPos,
        @Valid @Min(0) @RequestParam int yPos,
        @Valid @Min(0) @RequestParam int xDist,
        @Valid @Min(0) @RequestParam int yDist,
        @RequestParam String name,
        @RequestParam String projectId
    )
    {
        String userId = Session.userIdContext();
        var newPackage = new UMLPackage();
        newPackage.setPackageId(ID.generate());
        newPackage.setContentName(name);
        newPackage.setXPos(xPos);
        newPackage.setYPos(yPos);
        newPackage.setXDist(xDist);
        newPackage.setYDist(yDist);
        newPackage.setProjectId(projectId);
        packageService.validatePackage(xPos, yPos, xDist, yDist);
        try {
            packageService.savePackage(userId, newPackage);
            return new ResponseEntity<>(
                Serializer.serialize(newPackage),
                HttpStatus.OK
            );
        } catch(DataAccessException | IOException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    /**
     * Retrieve all packages for a project
     *
     * @param projectId id of the project
     * @return packages in a json array or failure
     */
    @ResponseBody
    @RequestMapping(value="/packages/forProject", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> findPackagesForProject(@RequestParam String projectId)
    {
        String userId = Session.userIdContext();
        try {
            return new ResponseEntity<>(
                Serializer.serialize(
                    packageService.retrieveAllPackages(userId, projectId)
                ),
                HttpStatus.OK
            );
        } catch(DataAccessException | IOException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Move a package on the diagram canvas
     *
     * @param packageId id of package to move
     * @param x new x position, min 0
     * @param y new y position, min 0
     * @param xDist x length of the package on the canvas,
     *              causes response failure code if it extends outside of the diagram canvas, min 0
     * @param yDist y length of the package on the canvas,
     *              causes response failure code if it extends outside of the diagram canvas, min 0
     * @return success or failure
     */
    @ResponseBody
    @RequestMapping(value="/packages/move", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> movePackage(
        @RequestParam(value= "package_id") String packageId,
        @Valid @Min(0) @RequestParam(value="x_pos") int x,
        @Valid @Min(0) @RequestParam(value="y_pos") int y,
        @Valid @Min(0) @RequestParam(value="x_dist") int xDist,
        @Valid @Min(0) @RequestParam(value="y_dist") int yDist
    )
    {
        String userId = Session.userIdContext();
        try {
            packageService.reFramePackage(userId, packageId, x, y, xDist, yDist);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(DataAccessException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Rename the UML package
     *
     * @param packageId id of package to rename
     * @param packageName new name
     * @return success or failure
     */
    @ResponseBody
    @RequestMapping(value="/packages/rename", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> renamePackage(
        @RequestParam(value="package_id") String packageId,
        @RequestParam(value="name") String packageName
    )
    {
        String userId = Session.userIdContext();
        try {
            packageService.renamePackage(userId, packageId, packageName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(DataAccessException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete a single package
     *
     * @param packageId id of package to delete
     * @return success or failure
     */
    @ResponseBody
    @RequestMapping(value="/packages/delete", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> deletePackage(
        @RequestParam(value="package_id") String packageId
    )
    {
        String userId = Session.userIdContext();
        try {
            packageService.deletePackage(userId, packageId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(DataAccessException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ExceptionHandler({ConstraintViolationException.class, InvalidInputException.class})
    public ResponseEntity<String> handleInputException(Exception ex)
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
