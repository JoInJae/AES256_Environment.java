package app.repository;

import app.data.entity.QUser;
import app.data.entity.QUser_Account;
import app.data.entity.User;
import app.data.entity.User_Account;
import app.repository.basement.Base_Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class User_Custom_Repository extends Base_Repository {

    QUser qUser = QUser.user;
    QUser_Account qUser_Account= QUser_Account.user_Account;

    protected User_Custom_Repository(JPAQueryFactory query) {
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

        return (user_account != null) ? Optional.of(user_account) : Optional.empty();

    }

    public Boolean exist_id(String id) {

        return query.selectOne().from(qUser_Account).where(qUser_Account.id.eq(id)).fetchFirst() != null;

    }

    public Optional<User_Account> user_account_get_by_id(String id) {

        User_Account user_account = query.selectFrom(qUser_Account).leftJoin(qUser_Account.user, qUser).fetchJoin().where(qUser_Account.id.eq(id)).fetchFirst();

        return (user_account != null) ? Optional.of(user_account) : Optional.empty();

    }

    public Long token_update(User_Account user_account, String refresh) {
        return query.update(qUser_Account).set(qUser_Account.refresh, refresh).where(qUser_Account.eq(user_account)).execute();
    }
}
