package app.data.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender {

    M("Male", "남자"),
    F("Female", "여자");

    private String eng;
    private String kor;

    @JsonCreator
    public static Gender from(String name){

        for (Gender gender : Gender.values()){

            if(gender.name().equals(name)) return gender;

        }

        return null;

    }

}
