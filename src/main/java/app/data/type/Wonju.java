package app.data.type;

import app.config.exception.basement.BaseException;
import app.data.response.type.Response;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Wonju {

    ROCK_SCISSOR_PAPER("rockscissorpaper","가위 바위 보","01"),

    HAND_CALCULATOR("handcalculator","손 계산기","02"),

    ORCHARD("orchard","과수원","03"),

    HEALTHY_LIFE("healthylife","건강한 생활","04"),

    DANCE_DANCE("dancedance","댄스 댄스","05");

    private String eng;
    private String kor;
    private String code;

    public static Wonju get(String code){

        for (Wonju wonju : Wonju.values()){

            if(wonju.code.equals(code)){
                return wonju;
            }

        }

        throw new BaseException(Response.WRONG_ENUM_CODE);

    }

    @JsonCreator
    public static Wonju from(String name){

        for (Wonju wonju : Wonju.values()){

            if(wonju.getEng().equals(name)) return wonju;

        }

        throw new BaseException(Response.WRONG_ENUM_NAME);

    }


}
