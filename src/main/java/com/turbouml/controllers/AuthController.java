package com.turbouml.controllers;

import com.turbouml.dto.Serializer;
import com.turbouml.exceptions.InvalidTokenException;
import com.turbouml.services.AuthService;
import com.turbouml.utils.Session;
import com.turbouml.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * Provides endpoints for authorizing/logging in users to the API
 */
@CrossOrigin
@RestController
public class AuthController
{
    private final AuthService authService;

    @Autowired
    public AuthController(@Qualifier("AuthorizationService") AuthService authService) {
        this.authService = authService;
    }

    /**
     * Login the user to the API's secure system by establishing a secure session
     *
     * @param idToken a google ID token
     * @return success or failure response code
     */
    @ResponseBody
    @RequestMapping(value="/auth/login", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> login(@RequestParam String idToken)
    {
        try {
            Session.setUserForContext(authService.authenticateUser(idToken));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (GeneralSecurityException | IOException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Logs the user out of the API's session
     *
     * @return success or failure response code
     */
    @ResponseBody
    @RequestMapping(value="/auth/logout", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> logout()
    {
        Session.removeUserForContext();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Retrieve the account information for the logged in user
     *
     * @return account information or failure response code
     */
    @ResponseBody
    @RequestMapping(value="/auth/account", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> account()
    {
        try {
            return new ResponseEntity<>(
                Serializer.serialize(Session.userContext()),
                HttpStatus.OK
            );
        } catch(IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<String> invalidToken(InvalidTokenException ex)
    {
        return new ResponseEntity<>(
            ResponseUtils.getResponse(ex.getMessage()),
            HttpStatus.UNAUTHORIZED
        );
    }
}
