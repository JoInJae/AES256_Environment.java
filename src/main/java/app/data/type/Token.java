package app.data.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Token {

    ACCESS(1000L * 30),
    REFRESH(1000L * 60 * 5);

    private Long expiration;

}
