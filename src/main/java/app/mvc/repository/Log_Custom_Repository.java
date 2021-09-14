package app.mvc.repository;

import app.data.entity.part.log.QLogV1;
import app.data.entity.part.log.QLogV2;
import app.data.entity.part.log.QLogV3;
import app.data.request.LogDTO;
import app.mvc.repository.basement.Base_Repository;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
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

}
