package app.data.response;

import app.data.response.type.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import javax.annotation.Nullable;

@AllArgsConstructor
@Builder
@Getter
public class Message <B>{

    private final String code;
    private final String message;
    @Nullable private final B body;

    public static <B> Message <B> ok() {

        return Message.<B>builder()
                .code(Response.SUCCESS.getStatus()).message(Response.SUCCESS.getMessage())
                .build();

    }

    public static <B> Message <B> ok(@Nullable B body) {

        return Message.<B>builder()
                .code(Response.SUCCESS.getStatus()).message(Response.SUCCESS.getMessage()).body(body)
                .build();

    }

    public static <B>Message <B> response(Response response){

        return Message.<B>builder()
                .code(response.getStatus()).message(response.getMessage())
                .build();

    }

}
