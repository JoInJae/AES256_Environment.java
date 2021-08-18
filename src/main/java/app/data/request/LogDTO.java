package app.data.request;

import app.data.entity.part.log.Log;
import app.data.entity.part.user.User;
import app.data.type.Production;
import com.google.gson.Gson;
import lombok.Getter;
import java.util.List;
import java.util.Map;

public class LogDTO {

    @Getter
    public static class Input{

        private List<Map<String, Object>> rawdata;
        private String name;
        private Long level;
        private Long correctCount;
        private Long totalCount;
        private Float acquiredScore;
        private Float totalScore;

        public Log toEntity(User user){

            return Log.builder()
                    .rawdata(new Gson().toJson(rawdata)).user(user)
                    .name(name).level(level).score_acquired(acquiredScore).score_total(totalScore)
                    .count_correct(correctCount).count_total(totalCount)
                    .build();

        }

    }

}
