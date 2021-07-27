package app.controller.exception;

import app.data.response.Message;
import app.exception.InvalidAuthorizationException;
import app.exception.WrongAccountException;
import app.exception.basement.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Exception_Controller {

    @ExceptionHandler({InvalidAuthorizationException.class})
    public ResponseEntity<Message<Void>> authentication(InvalidAuthorizationException e){

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Message.response(e.getResponse()));

    }

    @ExceptionHandler({WrongAccountException.class})
    public ResponseEntity<Message<Void>> account(WrongAccountException e){

        return ResponseEntity.ok(Message.response(e.getResponse()));

    }

    @ExceptionHandler({BaseException.class})
    public ResponseEntity<Message<Void>> base(BaseException e){

        return ResponseEntity.ok(Message.response(e.getResponse()));

    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> runtime(RuntimeException e){

        System.err.println(e.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

    }

}
