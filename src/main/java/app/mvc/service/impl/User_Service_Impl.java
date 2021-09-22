package app.mvc.service.impl;

import app.data.entity.embeded.Password;
import app.data.entity.part.user.User;
import app.data.response.Message;
import app.data.response.MessageB;
import app.data.type.Production;
import app.data.type.Token;
import app.data.entity.part.user.User_Account;
import app.data.request.UserDTO;
import app.data.response.type.Response;
import app.exception.InvalidAuthorizationException;
import app.exception.WrongAccountException;
import app.exception.basement.BaseException;
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

    @Transactional
    @Override
    public Message user_modify(UserDTO.Update param, String uuid) {

        Optional<User> is_user = repository.user_get(uuid);

        if(is_user.isEmpty()) throw new BaseException(Response.ERROR_ENTITY);

        User user = is_user.get();

        long check = repository.user_info_update(user, param);

        if(check >0) return Message.ok();


        return Message.ok();

    }

    @Transactional
    @Override
    public Message user_password_modify(UserDTO.Password param, String uuid) {

        Optional<User> is_user = repository.user_get(uuid);

        if(is_user.isEmpty()) throw new BaseException(Response.ERROR_ENTITY);

        long check = repository.user_password_update(param.getPassword(), is_user.get());

        if(check >0) return Message.ok();

        return Message.ok();

    }

    @Override
    public Message user_id_check(UserDTO.ID_Check param) {

        boolean check = repository.exist_id(param.getId());

        if(check) throw new WrongAccountException(Response.FAIL_ID_DUPLICATE);

        return Message.ok();

    }

    @Override
    public Message user_password_check(UserDTO.Password param, String uuid) {

       Optional<Password> is_password = repository.password_get(uuid);

        if(is_password.isEmpty()) throw new BaseException(Response.ERROR_ENTITY);


        if(is_password.get().match(param.getPassword())){
            return Message.ok();
        }

        throw new WrongAccountException(Response.WRONG_PASSWORD);

    }

    @Transactional
    @Override
    public MessageB<UserDTO> user_login_check(UserDTO.Login_Check param) {

        Optional<User_Account> is_user_account = repository.user_account_get_by_id(param.getId());

        if(is_user_account.isEmpty()) throw new InvalidAuthorizationException(Response.FAIL_ID);

        User_Account user_account = is_user_account.get();

        if(!(user_account.getPassword().match(param.getPassword()))) throw new InvalidAuthorizationException(Response.FAIL_PASSWORD);

        User user =  user_account.getUser();

        String access = jwt.create(Token.ACCESS, "user", user.getUuid());
        String refresh = jwt.create(Token.REFRESH, "user", user.getUuid());

        if(repository.token_update(user_account, refresh) <= 0) throw new InvalidAuthorizationException(Response.ERROR_SQL);

        if(Production.WONJU.equals(user_account.getUser().getProduction())){

            return MessageB.ok(
                    UserDTO.Login_Check_WonJu_Result.builder().access(access).refresh(refresh)
                            .birth(user.getBirth()).gender(user.getGender()).name(user.getName()).education(user.getEducation()).build());

        }

        return MessageB.ok(UserDTO.Login_Check_Result.builder().access(access).refresh(refresh).build());

    }

    @Override
    public MessageB<List<UserDTO.Simple_Info_Result>> user_get_all(Production production) {

        return MessageB.ok(repository.user_get_all(production));

    }

    @Override
    public MessageB<UserDTO.Info_Result> user_get(String uuid) {

        return MessageB.ok(repository.user_get_by_uuid(uuid));

    }

}
