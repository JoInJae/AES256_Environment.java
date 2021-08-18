package app.data.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender {

    M("Male", "남자"),
    F("Female", "여자");

    private String eng;
    private String kor;

}
