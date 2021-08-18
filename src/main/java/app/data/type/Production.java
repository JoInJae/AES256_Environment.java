package app.data.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Production {

    XR("XR"),
    WONJU("원주");

    private String name;

}
