package app.repository.user;

import app.data.entity.user.QUser;
import app.data.entity.user.QUser_Account;
import app.data.entity.user.User;
import app.data.entity.user.User_Account;
import app.repository.basement.Repository_Abstract;

import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class User_Repository extends Repository_Abstract {

    QUser qUser = QUser.user;
    QUser_Account qUser_Account= QUser_Account.user_Account;

    protected User_Repository(JPAQueryFactory query) {
        super(query);
    }

    public Optional<User> user_get(String uuid){

        User user = query.selectFrom(qUser).where(qUser.uuid.eq(uuid)).fetchFirst();

        return user != null ? Optional.of(user) : Optional.empty();

    }

    public Optional<User> user_get(){

        User user = query.selectFrom(qUser).fetchFirst();

        return user != null ? Optional.of(user) : Optional.empty();

    }

    public Optional<User_Account> user_account_get(String uuid){

        User_Account user_account = query.selectFrom(qUser_Account).where(qUser_Account.user.uuid.eq(uuid)).fetchFirst();

        return user_account != null ? Optional.of(user_account) : Optional.empty();

    }

}
