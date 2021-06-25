package app.service.impl;

import app.data.entity.user.User;
import app.data.entity.user.User_Account;
import app.data.request.UserDTO;
import app.repository.user.User_Repository;
import app.service.User_Service;

import app.service.basement.Service_Abstract;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class User_Service_Impl extends Service_Abstract<User_Repository> implements User_Service{

    public User_Service_Impl(User_Repository user_repository) {
        super(user_repository);
    }

    @Transactional
    @Override
    public void user_put(UserDTO.Input param) {

        User user = param.to_user();
        User_Account user_account = param.to_user_account(user);

        em.persist(user_account);

    }

}
