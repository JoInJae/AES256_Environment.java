package app.data.request;

import lombok.Getter;

public class Test {
    @Getter
    public static class Insert extends Test{
        private String name;
        private String id;
    }
}
