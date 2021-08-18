package app.mvc.service.impl;

import app.data.response.Message;
import app.data.response.MessageB;
import app.data.type.Production;
import app.data.type.Token;
import app.data.entity.part.user.User_Account;
import app.data.request.UserDTO;
import app.data.response.type.Response;
import app.exception.InvalidAuthorizationException;
import app.exception.WrongAccountException;
import app.mvc.repository.User_Custom_Repository;
import app.mvc.service.User_Service;
import app.mvc.service.basement.Base_Service;
import app.utility.JWT;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class User_Service_Impl extends Base_Service<User_Custom_Repository> implements User_Service{

    private final JWT jwt;

    public User_Service_Impl(User_Custom_Repository user_repository, JWT jwt) {
        super(user_repository);
        this.jwt = jwt;
    }

    @Transactional
    @Override
    public Message user_put(UserDTO.Input param) {

        User_Account user_account = param.to_entity();

        em.persist(user_account);

        return Message.ok();

    }

    @Override
    public Message user_id_check(UserDTO.ID_Check param) {

        boolean check = repository.exist_id(param.getId());

        if(check) throw new WrongAccountException(Response.FAIL_ID_DUPLICATE);

        return Message.ok();

    }

    @Transactional
    @Override
    public MessageB<UserDTO.Login_Check_Result> user_login_check(UserDTO.Login_Check param) {

        Optional<User_Account> is_user_account = repository.user_account_get_by_id(param.getId());

        if(is_user_account.isEmpty()) throw new InvalidAuthorizationException(Response.FAIL_ID);

        User_Account user_account = is_user_account.get();

        if(!(user_account.getPassword().match(param.getPassword()))) throw new InvalidAuthorizationException(Response.FAIL_PASSWORD);

        String uuid = user_account.getUser().getUuid();
        String access = jwt.create(Token.ACCESS, "user", uuid);
        String refresh = jwt.create(Token.REFRESH, "user", uuid);

        if(repository.token_update(user_account, refresh) <= 0) throw new InvalidAuthorizationException(Response.ERROR_SQL);

        return MessageB.ok(UserDTO.Login_Check_Result.builder().access(access).refresh(refresh).build());

    }

    @Override
    public MessageB<List<UserDTO.Simple_Info_Result>> user_get_all(Production production) {

        return MessageB.ok(repository.user_get_all(production));

    }

}
