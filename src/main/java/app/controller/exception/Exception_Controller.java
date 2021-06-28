package app.controller.exception;

import app.exception.InvalidAuthorizationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Exception_Controller {

    @ExceptionHandler({InvalidAuthorizationException.class})
    public @ResponseBody ResponseEntity authentication(InvalidAuthorizationException e){

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());

    }

}
