package app.exception;

public class InvalidAuthorizationException extends RuntimeException{

    private static final long serialVersionUID = -8358132288998849182L;

    private static final String message = "검증되지 않은 인증입니다";

    public InvalidAuthorizationException() {
        super(message);
    }

}
