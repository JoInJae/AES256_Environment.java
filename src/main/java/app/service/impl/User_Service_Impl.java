package app.service.impl;

import app.data.request.type.Token;
import app.data.entity.User;
import app.data.entity.User_Account;
import app.data.request.UserDTO;
import app.data.response.Message;
import app.data.response.type.Response;
import app.exception.InvalidAuthorizationException;
import app.exception.WrongAccountException;
import app.repository.User_Custom_Repository;
import app.service.User_Service;
import app.service.basement.Base_Service;
import app.utility.JWT;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
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
    public Message<Void> user_put(UserDTO.Input param) {

        User user = param.to_user();

        User_Account user_account = param.to_user_account(user);

        em.persist(user_account);

        return Message.ok();

    }

    @Override
    public Message<Void> user_id_check(UserDTO.ID_Check param) {

        boolean check = repository.exist_id(param.getId());

        if(check) throw new WrongAccountException(Response.FAIL_ID_DUPLICATE);

        return Message.ok();

    }

    @Transactional
    @Override
    public Message<UserDTO.Login_Check_Result> user_login_check(UserDTO.Login_Check param) {

        Optional<User_Account> is_user_account = repository.user_account_get_by_id(param.getId());

        if(is_user_account.isEmpty()) throw new InvalidAuthorizationException(Response.FAIL_ID);

        User_Account user_account = is_user_account.get();

        if(!(user_account.match(param.getPassword()))) throw new InvalidAuthorizationException(Response.FAIL_PASSWORD);

        String uuid = user_account.getUser().getUuid();
        String access = jwt.create(Token.ACCESS, uuid);
        String refresh = jwt.create(Token.REFRESH, uuid);

        if(repository.token_update(user_account, refresh) <= 0) throw new InvalidAuthorizationException(Response.FAIL_ID);

        return Message.ok(UserDTO.Login_Check_Result.builder().access(access).refresh(refresh).build());

    }

}
