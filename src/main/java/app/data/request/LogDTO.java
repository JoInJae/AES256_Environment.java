package app.data.request;

import app.data.entity.Log;
import app.data.entity.User;
import app.data.request.type.Production;
import com.google.gson.Gson;
import lombok.Getter;
import java.util.List;
import java.util.Map;

public class LogDTO {

    @Getter
    public static class Input{

        private String name;
        private List<Map<String, Object>> rawdata;
        private Long level;
        private Long correctCount;
        private Long totalCount;
        private Float acquiredScore;
        private Float totalScore;
        private Production production;

        public Log toEntity(User user){

            return Log.builder()
                    .rawdata(new Gson().toJson(rawdata)).user(user).production(production)
                    .name(name).level(level).score_1(acquiredScore).score_2(totalScore)
                    .count_1(correctCount).count_2(totalCount)
                    .build();

        }
    }

}
