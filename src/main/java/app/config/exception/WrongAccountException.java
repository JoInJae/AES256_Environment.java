package app.config.exception;

import app.data.response.type.Response;
import lombok.Getter;

@Getter
public class WrongAccountException extends RuntimeException{

    private static final long serialVersionUID = 5025943988172830156L;

    private Response response;

    public WrongAccountException(Response response) {
        super(response.getMessage());
        this.response = response;
    }
}
