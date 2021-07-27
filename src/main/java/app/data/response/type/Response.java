package app.data.response.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Response {

    SUCCESS("0000", "Success Your Work"),

    FAIL_ID("0001", "Check Your Account"),
    FAIL_PASSWORD("0002", "Check Your Account"),
    FAIL_ID_DUPLICATE("0003", "Check Your Account"),
    FAIL_PASSWORD_DUPLICATE("0004", "Check Your Account"),
    FAIL_TOKEN_NOT_EXIST("0005", "Check Your Token"),
    FAIL_TOKEN_INVALID("0006", "Check Your Token"),
    FAIL_TOKEN_TIMEOUT("0007", "Check Your Token"),

    FAIL_PARAMETER("0010", "Check Your Parameter"),

    ERROR_ENTITY("0101", "If you continue to have problems, Please contact us"),
    ERROR_SQL("0102", "If you continue to have problems, Please contact us");

    private final String status;
    private final String message;

}
