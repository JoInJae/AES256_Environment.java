package app.data.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Token {

    ACCESS(1000L * 60 * 5),
    REFRESH(1000L * 60 * 60 * 24 * 180);

    private Long expiration;

}
