package app.mvc.service.impl;

import app.data.entity.part.log.Log;
import app.data.entity.part.log.LogV1;
import app.data.entity.part.log.LogV2;
import app.data.entity.part.log.LogV3;
import app.data.entity.part.user.User;
import app.data.request.LogDTO;
import app.data.response.Message;
import app.data.response.MessageB;
import app.data.response.type.Response;
import app.config.exception.WrongApproachEntityException;
import app.mvc.repository.Log_Custom_Repository;
import app.mvc.repository.part.user.User_Repository;
import app.mvc.service.Log_Service;
import app.mvc.service.basement.Base_Service;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class Log_Service_Impl extends Base_Service<Log_Custom_Repository> implements Log_Service{

    private final User_Repository user_repository;

    public Log_Service_Impl(Log_Custom_Repository log_repository, User_Repository user_repository) {
        super(log_repository);
        this.user_repository = user_repository;
    }

    @Transactional
    @Override
    public Message log_put(LogDTO.V1 param, String uuid) {

        Optional<User> is_user = user_repository.findByUuid(uuid);

        if(is_user.isEmpty()) throw new WrongApproachEntityException(Response.ERROR_ENTITY);

        LogV1 log = param.toEntity(is_user.get());

        em.persist(log);

        return Message.ok();

    }

    @Transactional
    @Override
    public Message log_put(LogDTO.V2 param, String uuid) {

        Optional<User> is_user = user_repository.findByUuid(uuid);

        if(is_user.isEmpty()) throw new WrongApproachEntityException(Response.ERROR_ENTITY);

        LogV2 log = param.toEntity(is_user.get());

        em.persist(log);

        return Message.ok();
    }

    @Transactional
    @Override
    public Message log_put(LogDTO.V3 param, String uuid) {

        Optional<User> is_user = user_repository.findByUuid(uuid);

        if(is_user.isEmpty()) throw new WrongApproachEntityException(Response.ERROR_ENTITY);

        LogV3 log = param.toEntity(is_user.get());

        em.persist(log);

        return Message.ok();

    }

    @Transactional
    @Override
    public Message log_put(LogDTO.Basic param, String uuid) {

        Optional<User> is_user = user_repository.findByUuid(uuid);

        if(is_user.isEmpty()) throw new WrongApproachEntityException(Response.ERROR_ENTITY);

        Log log = param.toEntity(is_user.get());

        em.persist(log);

        return Message.ok();

    }

    @Override
    public MessageB<LogDTO.Stat> stat_get(String uuid) {

        LocalDateTime now = LocalDateTime.now();

        LocalDateTime tmp= LocalDateTime.of(now.toLocalDate(), LocalTime.of(0,0,0));

        LocalDateTime start;

        switch (now.getDayOfWeek()){
            case TUESDAY:
                start = tmp.minusDays(1); break;
            case WEDNESDAY:
                start = tmp.minusDays(2) ; break;
            case THURSDAY:
                start = tmp.minusDays(3) ; break;
            case FRIDAY:
                start = tmp.minusDays(4) ; break;
            case SATURDAY:
                start = tmp.minusDays(5); break;
            case SUNDAY:
                start = tmp.minusDays(6) ; break;
            case MONDAY:
            default:
                start = tmp; break;
        }

        List<LogDTO.Stat_This_Week_Tmp> tmps = repository.this_week_ratio_get(start, now, uuid);

        LogDTO.Stat result = new LogDTO.Stat();

        result.setBest_score(repository.best_score_get(uuid));

        result.setBest_time(repository.best_time_get(uuid));

        long total = 0;

        Map<String, LogDTO.Stat_This_Week_Total_Tmp> total_tmp_map = new HashMap<>();

        for (LogDTO.Stat_This_Week_Tmp this_week_tmp : tmps){

                total = total + this_week_tmp.getCount();

                if(total_tmp_map.size() ==0 || !total_tmp_map.containsKey(this_week_tmp.getName())){

                    LogDTO.Stat_This_Week_Total_Tmp total_tmp
                            = new LogDTO.Stat_This_Week_Total_Tmp(this_week_tmp.getCount(), this_week_tmp.getTime(), this_week_tmp.getScore());

                    total_tmp_map.put(this_week_tmp.getName(),  total_tmp);

                }else{

                    LogDTO.Stat_This_Week_Total_Tmp total_tmp = total_tmp_map.get(this_week_tmp.getName());

                    total_tmp.setCount(total_tmp.getCount()+ this_week_tmp.getCount());
                    total_tmp.setTime(total_tmp.getTime()+ this_week_tmp.getTime());
                    total_tmp.setScore(total_tmp.getScore()+ this_week_tmp.getScore());

                }

                result.getTimePerDay().add(
                        new LogDTO.TimePerDay(this_week_tmp.getName(), LocalDate.parse(this_week_tmp.getDate()).getDayOfWeek().name(), this_week_tmp.getTime()));

        }

        if(total >0){

            for (String key : total_tmp_map.keySet() ){

                result.getRatio().add(new LogDTO.Ratio(key, (int)(total_tmp_map.get(key).getCount()* 100 / total)));
                result.getTimePerWeek().add(new LogDTO.TimePerWeek(key, total_tmp_map.get(key).getTime()));

            }

        }

        return MessageB.ok(result);

    }

    @Override
    public MessageB<List<LogDTO.Game_Info>> game_info_get(String uuid) {

        return MessageB.ok(repository.game_info_get(uuid));

    }

    @Override
    public MessageB<List<LogDTO.LogHistory>> log_list_get(String uuid) {

        return MessageB.ok(repository.log_list_get(uuid));

    }

}
