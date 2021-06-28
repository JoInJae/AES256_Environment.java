package app.service.impl;

import app.data.entity.type.Token;
import app.data.entity.user.User;
import app.data.entity.user.User_Account;
import app.data.request.UserDTO;
import app.exception.InvalidAuthorizationException;
import app.repository.user.User_Repository;
import app.service.User_Service;
import app.service.basement.Service_Abstract;
import app.utility.JWT;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class User_Service_Impl extends Service_Abstract<User_Repository> implements User_Service{

    private final JWT jwt;

    public User_Service_Impl(User_Repository user_repository, JWT jwt) {
        super(user_repository);
        this.jwt = jwt;
    }

    @Transactional
    @Override
    public void user_put(UserDTO.Input param) {

        User user = param.to_user();
        User_Account user_account = param.to_user_account(user);

        em.persist(user_account);

    }

    @Override
    public void user_id_check(UserDTO.ID_Check param) {

        boolean check = repository.exist_id(param.getId());

        if(!(check)) throw new InvalidAuthorizationException();

    }

    @Transactional
    @Override
    public UserDTO.Login_Check_Result user_login_check(UserDTO.Login_Check param) {

        Optional<User_Account> is_user_account = repository.user_account_get_by_id(param.getId());

        if(is_user_account.isEmpty()) throw new InvalidAuthorizationException();

        User_Account user_account = is_user_account.get();

        if(!(user_account.match(param.getPassword()))) throw new InvalidAuthorizationException();

        String uuid = user_account.getUser().getUuid();
        String access = jwt.create(Token.ACCESS, uuid);
        String refresh = jwt.create(Token.REFRESH, uuid);

        if(repository.token_update(user_account, refresh) <= 0) throw new InvalidAuthorizationException();

        return UserDTO.Login_Check_Result.builder().access(access).refresh(refresh).build();

    }

}
