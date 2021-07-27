package app.exception.basement;

import app.data.response.type.Response;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{

    private static final long serialVersionUID = 5571586247209671126L;

    private final Response response;

    public BaseException(Response response) {
        super(response.getMessage());
        this.response = response;
    }

}
