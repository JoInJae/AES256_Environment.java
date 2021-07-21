package app.exception;

public class WrongApprochEntityException extends RuntimeException{

    private static final long serialVersionUID = -8358132288998849186L;

    private static final String message = "잘못된 객체 접근입니다.";

    public WrongApprochEntityException() {
        super(message);
    }

}
