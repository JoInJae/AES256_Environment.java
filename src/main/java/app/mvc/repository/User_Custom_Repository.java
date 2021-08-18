package app.mvc.repository;

import app.data.entity.part.user.QUser;
import app.data.entity.part.user.QUser_Account;
import app.data.entity.part.user.User;
import app.data.entity.part.user.User_Account;
import app.data.request.UserDTO;
import app.data.type.Production;
import app.mvc.repository.basement.Base_Repository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    public List<UserDTO.Simple_Info_Result> user_get_all(Production production) {

        BooleanBuilder builder = new BooleanBuilder();

        if(production != null){
            builder.and(qUser_Account.user.production.eq(production));
        }

        return query.from(qUser_Account).select(
                    Projections.constructor(UserDTO.Simple_Info_Result.class,
                            qUser_Account.id, qUser_Account.user.name, qUser_Account.user.gender,
                            qUser_Account.user.birth, qUser_Account.user.education, qUser_Account.user.production
                )).where(builder).fetch();

    }
}
