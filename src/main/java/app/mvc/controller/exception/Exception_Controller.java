package app.mvc.controller.exception;

import app.data.response.Message;
import app.config.exception.InvalidAuthorizationException;
import app.config.exception.WrongAccountException;
import app.config.exception.basement.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Exception_Controller {

    @ExceptionHandler({InvalidAuthorizationException.class})
    public ResponseEntity<Message> authentication(InvalidAuthorizationException e){

        return ResponseEntity.ok(Message.ok(e.getResponse()));

    }

    @ExceptionHandler({WrongAccountException.class})
    public ResponseEntity<Message> account(WrongAccountException e){

        return ResponseEntity.ok(Message.ok(e.getResponse()));

    }

    @ExceptionHandler({BaseException.class})
    public ResponseEntity<Message> base(BaseException e){

        return ResponseEntity.ok(Message.ok(e.getResponse()));

    }

}
