package app.data.request;

import lombok.ToString;

public class LogDTO {

    @ToString
    public static class Test extends  LogDTO{
        private String id;
        private String name;
    }

}
