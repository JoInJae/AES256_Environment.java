package app.data.request;

import app.data.entity.part.log.LogV1;
import app.data.entity.part.log.LogV2;
import app.data.entity.part.user.User;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;
import java.util.Map;

public class LogDTO {

    @AllArgsConstructor
    @Getter
    public static class V1{

        private List<Map<String, Object>> rawdata;
        private String name;
        private Long level;
        private Long correctCount;
        private Long totalCount;
        private Float acquiredScore;
        private Float totalScore;
        private Long limit;
        private Long time;

        public LogV1 toEntity(User user){

            return LogV1.builder()
                    .rawdata(new Gson().toJson(rawdata)).user(user)
                    .name(name).level(level).score_acquired(acquiredScore).score_total(totalScore)
                    .count_correct(correctCount).count_total(totalCount)
                    .limit(limit).time(time)
                    .build();

        }

    }

    @AllArgsConstructor
    @Getter
    public static class V2{

        private List<Map<String, Object>> rawdata;
        private String name;
        private Long time;

        public LogV2 toEntity(User user){

            return LogV2.builder()
                    .rawdata(new Gson().toJson(rawdata)).user(user)
                    .name(name).time(time)
                    .build();

        }

    }

    @AllArgsConstructor
    @Getter
    public static class V3{

        private List<Map<String, Object>> rawdata;
        private String name;
        private Long level;
        private Long type;
        private Long limit;
        private Long time;

    }

}
