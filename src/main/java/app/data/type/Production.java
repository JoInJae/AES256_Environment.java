package app.data.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Production {

    XR("XR"),
    WONJU("원주");

    private String name;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Production  from(String name){

        for (Production production : Production.values()){

            if (production.name().equals(name))return production;

        }

        return null;

    }

}
