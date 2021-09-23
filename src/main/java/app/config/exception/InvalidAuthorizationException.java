package app.config.exception;

import app.data.response.type.Response;
import app.config.exception.basement.BaseException;

public class InvalidAuthorizationException extends BaseException {

    private static final long serialVersionUID = -8358132288998849182L;

    public InvalidAuthorizationException(Response response) {
        super(response);
    }
}
