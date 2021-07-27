package app.service.impl;

import app.data.entity.Log;
import app.data.entity.User;
import app.data.request.LogDTO;
import app.data.response.Message;
import app.exception.WrongApprochEntityException;
import app.repository.Log_Custom_Repository;
import app.repository.part.user.User_Repository;
import app.service.Log_Service;
import app.service.basement.Base_Service;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class Log_Service_Impl extends Base_Service<Log_Custom_Repository> implements Log_Service{

    private User_Repository user_repository;

    public Log_Service_Impl(Log_Custom_Repository log_repository, User_Repository user_repository) {
        super(log_repository);
        this.user_repository = user_repository;
    }

    @Transactional
    @Override
    public Message<Void> log_put(LogDTO.Input param, String uuid) {

        Optional<User> is_user = user_repository.findByUuid(uuid);

        if(is_user.isEmpty()) throw new WrongApprochEntityException();

        Log log = param.toEntity(is_user.get());

        em.persist(log);

        return Message.ok();

    }

}
