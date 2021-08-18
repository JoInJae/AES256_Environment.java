package app.data.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Education {

    NONE("없음", "0", 0),
    ELEMENTARY("초등학교", "1 - 6", 1),
    MIDDLE("중학교", "7 - 9", 2),
    HIGH("고등학교", "10 - 12", 3),
    UNIVERSITY("대학교", "13 - 16", 4),
    ELITE("대학교이상", "16+", 5);

    private final String word;
    private final String range;
    private final Integer code;

    public static Education get(Integer code){

        for (Education education : Education.values()){
            if(education.getCode().equals(code)){
                return education;
            }
        }

        return null;

    }


}
