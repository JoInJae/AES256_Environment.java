package app.data.response;

import app.data.response.type.Response;
import lombok.Getter;
import javax.annotation.Nullable;

@Getter
public class MessageB<B> extends Message {

    @Nullable private final B body;

    public MessageB(String code, String message, @Nullable B body) {
        super(code, message);
        this.body = body;
    }

    public static <B> MessageB<B> ok(@Nullable B body) {

        return new MessageB<>(Response.SUCCESS.getStatus(), Response.SUCCESS.getMessage(), body);

    }

}
