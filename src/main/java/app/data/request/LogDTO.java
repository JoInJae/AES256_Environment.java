package app.data.request;

import app.data.entity.part.log.Log;
import app.data.entity.part.log.LogV1;
import app.data.entity.part.log.LogV2;
import app.data.entity.part.log.LogV3;
import app.data.entity.part.user.User;
import app.data.type.Wonju;
import com.google.gson.Gson;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LogDTO {

    @AllArgsConstructor
    @Getter
    public static class Basic{

        private List<Map<String, Object>> rawdata;
        private Wonju name;
        private Long level;
        private Float acquiredScore;
        private Float totalScore;

        public Log toEntity(User user){

            return Log.builder()
                    .rawdata(new Gson().toJson(rawdata)).user(user)
                    .wonju(name).level(level).score_acquired(acquiredScore).score_total(totalScore)
                    .build();

        }

    }



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

        public LogV3 toEntity(User user){

            return LogV3.builder()
                    .rawdata(new Gson().toJson(rawdata)).user(user)
                    .name(name).level(level).type(type).limit(limit).time(time)
                    .build();

        }

    }


    @Getter
    public static class Stat{

        @Setter
        private Best_Score best_score;
        @Setter
        private Best_Time best_time;
        private final List<Ratio> ratio = new ArrayList<>();
        private final List<TimePerWeek> timePerWeek = new ArrayList<>();
        private final List<TimePerDay> timePerDay = new ArrayList<>();

    }

    @AllArgsConstructor
    @Getter
    public static class Best_Score{

        private String name;
        private Float score;
        private Float comparedScore;

    }

    @AllArgsConstructor
    @Getter
    public static class Best_Score_Tmp{

        private String name;
        private Float score;

    }

    @AllArgsConstructor
    @Getter
    public static class Best_Time{

        private String name;
        private Long time;
        private Long comparedTime;

    }

    @AllArgsConstructor
    @Getter
    public static class Best_Time_Tmp{

        private String name;
        private Long time;

    }

    @Getter
    public static class Stat_This_Week_Tmp{

        private final String name;
        private final Long count;
        private final Long time;
        private final Float score;
        private final String date;

        @Builder
        public Stat_This_Week_Tmp(String name, Long count, Long time, Float score, String date) {
            this.name = name;
            this.count = count;
            this.time = time;
            this.score = score;
            this.date = date;
        }
        @Builder
        public Stat_This_Week_Tmp(String name, Long count, Long time, String date) {
            this.name = name;
            this.count = count;
            this.time = time;
            this.score = 0F;
            this.date = date;
        }
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Stat_This_Week_Total_Tmp{

        private Long count;
        private Long time;
        private Float score;

    }

    @AllArgsConstructor
    @Getter
    public static class Ratio{

        private String name;
        private Integer ratio;

    }

    @AllArgsConstructor
    @Getter
    public static class TimePerWeek{

        private String name;
        private Long time;

    }

    @AllArgsConstructor
    @Getter
    public static class TimePerDay{

        private String name;
        private String day;
        private Long time;

    }

}
