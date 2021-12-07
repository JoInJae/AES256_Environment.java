package app.mvc.repository;

import app.data.entity.part.log.QLog;
import app.data.entity.part.log.QLogV1;
import app.data.entity.part.log.QLogV2;
import app.data.entity.part.log.QLogV3;
import app.data.request.LogDTO;
import app.mvc.repository.basement.Base_Repository;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public class Log_Custom_Repository extends Base_Repository {

    QLogV1 qLogV1 = QLogV1.logV1;
    QLogV2 qLogV2 = QLogV2.logV2;
    QLogV3 qLogV3 = QLogV3.logV3;
    QLog qLog = QLog.log;

    protected Log_Custom_Repository(JPAQueryFactory query) {
        super(query);
    }

    public List<LogDTO.Stat_This_Week_Tmp>  this_week_ratio_get(LocalDateTime start, LocalDateTime end, String uuid){

        StringTemplate formattedV1Date = Expressions.stringTemplate("DATE_FORMAT({0}, {1})", qLogV1.createTime , ConstantImpl.create("%Y-%m-%d"));
        StringTemplate formattedV2Date = Expressions.stringTemplate("DATE_FORMAT({0}, {1})", qLogV2.createTime , ConstantImpl.create("%Y-%m-%d"));
        StringTemplate formattedV3Date = Expressions.stringTemplate("DATE_FORMAT({0}, {1})", qLogV3.createTime , ConstantImpl.create("%Y-%m-%d"));


        List<LogDTO.Stat_This_Week_Tmp> v1 = query.from(qLogV1)
                .select(Projections.constructor(LogDTO.Stat_This_Week_Tmp.class,
                        qLogV1.name,
                        qLogV1.name.count(),
                        qLogV1.time.sum(),
                        qLogV1.score.score_1,
                        formattedV1Date
                ))
                .groupBy(qLogV1.name, formattedV1Date)
                .where(qLogV1.user.uuid.eq(uuid).and(qLogV1.createTime.between(start, end))).fetch();

        List<LogDTO.Stat_This_Week_Tmp>v2 = query.from(qLogV2)
                .select(Projections.constructor(LogDTO.Stat_This_Week_Tmp.class,
                        qLogV2.name,
                        qLogV2.count(),
                        qLogV2.time.sum(),
                        formattedV2Date
                ))
                .groupBy(qLogV2.name, formattedV2Date)
                .where(qLogV2.user.uuid.eq(uuid).and(qLogV2.createTime.between(start, end))).fetch();

        v1.addAll(v2);

        List<LogDTO.Stat_This_Week_Tmp>v3 = query.from(qLogV3)
                .select(Projections.constructor(LogDTO.Stat_This_Week_Tmp.class,
                        qLogV3.name,
                        qLogV3.name.count(),
                        qLogV3.time.sum(),
                        formattedV3Date
                ))
                .groupBy(qLogV3.name, formattedV3Date)
                .where(qLogV3.user.uuid.eq(uuid).and(qLogV3.createTime.between(start, end))).fetch();

        v1.addAll(v3);

        return v1;

    }

    public LogDTO.Best_Score best_score_get(String uuid) {

        StringTemplate formattedDate = Expressions.stringTemplate("DATE_FORMAT({0}, {1})", qLogV1.createTime , ConstantImpl.create("%Y-%m-%d"));
        LocalDate now = LocalDate.now();

        Map<String, Float> map = query.from(qLogV1)
                .where(qLogV1.user.uuid.eq(uuid).and(formattedDate.notIn(now.toString())))
                .transform(GroupBy.groupBy(qLogV1.name).as(qLogV1.score.score_1.max()));


        List<LogDTO.Best_Score_Tmp> game_tmp_results = query.from(qLogV1).select(Projections.constructor(
                    LogDTO.Best_Score_Tmp.class,
                        qLogV1.name, qLogV1.score.score_1.max()
                ))
                .groupBy(qLogV1.name)
                .where(qLogV1.user.uuid.eq(uuid).and(formattedDate.eq(now.toString()))).fetch();

        if(game_tmp_results.size() > 0 ) {

            String name = "";
            float score = 0 ;

            for (LogDTO.Best_Score_Tmp game_tmp_result : game_tmp_results) {

                if ("".equals(name)) {

                    name = game_tmp_result.getName();
                    score = game_tmp_result.getScore();

                } else {

                    if (score < game_tmp_result.getScore()) {

                        name = game_tmp_result.getName();
                        score = game_tmp_result.getScore();

                    }

                }

            }

            float compare = (!map.containsKey(null)) ? score - ((map.get(name) != null) ? map.get(name) : 0) : 0;

            return new LogDTO.Best_Score(name, score, compare);

        }else{

            return null;

        }

    }

    public LogDTO.Best_Time best_time_get(String uuid) {

        StringTemplate formattedDate = Expressions.stringTemplate("DATE_FORMAT({0}, {1})", qLogV3.createTime , ConstantImpl.create("%Y-%m-%d"));
        LocalDate now = LocalDate.now();

        Map<String, Long> map = query.from(qLogV3)
                .where(qLogV3.user.uuid.eq(uuid).and(formattedDate.notIn(now.toString())))
                .transform(GroupBy.groupBy(qLogV3.name).as(qLogV3.time.min()));


        LogDTO.Best_Time_Tmp time_tmp = query.from(qLogV3)
                .select(Projections.constructor(LogDTO.Best_Time_Tmp.class,
                        qLogV3.name, qLogV3.time.min()
                ))
                .groupBy(qLogV3.name).where(qLogV3.user.uuid.eq(uuid).and(formattedDate.eq(now.toString()))).fetchFirst();

        if(time_tmp != null) {

            long compare = !map.containsKey(null) ? (map.get(time_tmp.getName()) != null ? map.get(time_tmp.getName()) : 0) - time_tmp.getTime() : 0;

            return new LogDTO.Best_Time(time_tmp.getName(), time_tmp.getTime(), compare);

        }

        return  null;

    }

    public List<LogDTO.Game_Info> game_info_get(String uuid) {

        return query.from(qLog)
                .select(Projections.constructor(LogDTO.Game_Info.class,
                        qLog.wonju, qLog.next))
                .orderBy(qLog.wonju.asc())
                .where(qLog.idx.in(JPAExpressions
                        .select(qLog.idx.max())
                        .from(qLog)
                        .groupBy(qLog.wonju)
                        .where(qLog.user.uuid.eq(uuid))))
                .fetch();

    }

    public List<LogDTO.LogHistory> log_list_get(String uuid) {

        return query.from(qLog)
                .select(Projections.constructor(LogDTO.LogHistory.class,
                        qLog.wonju, qLog.present, qLog.score.score_1, qLog.score.score_2, qLog.createTime
                ))
                .where(qLog.user.uuid.eq(uuid))
                .orderBy(qLog.createTime.desc())
                .fetch();

    }

    public String log_get_last_data(String name, String uuid) {

        if("family".equals(name)){
            return query.from(qLogV2).select(qLogV2.rawdata).where(qLogV2.name.eq(name).and(qLogV2.user.uuid.eq(uuid))).orderBy(qLogV2.idx.desc()).fetchFirst();
        }else if("shopping".equals(name)){
            return query.from(qLogV3).select(qLogV3.rawdata).where(qLogV3.name.eq(name).and(qLogV3.user.uuid.eq(uuid))).orderBy(qLogV3.idx.desc()).fetchFirst();
        }else{
            return query.from(qLogV1).select(qLogV1.rawdata).where(qLogV1.name.eq(name).and(qLogV1.user.uuid.eq(uuid))).orderBy(qLogV1.idx.desc()).fetchFirst();
        }

    }
}
