package app.data.response;

import app.data.response.type.Response;
import lombok.Getter;

@Getter
public class Message {

    private final String code;
    private final String message;

    public Message(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Message ok() {

        return new Message(Response.SUCCESS.getStatus(), Response.SUCCESS.getMessage());

    }

    public static Message ok(Response response) {

        return new Message(response.getStatus(), response.getMessage());

    }

}
