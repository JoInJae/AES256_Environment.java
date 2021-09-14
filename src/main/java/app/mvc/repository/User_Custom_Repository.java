package app.mvc.repository;

import app.data.entity.embeded.Birth;
import app.data.entity.embeded.Email;
import app.data.entity.embeded.Password;
import app.data.entity.part.user.QUser;
import app.data.entity.part.user.QUser_Account;
import app.data.entity.part.user.User;
import app.data.entity.part.user.User_Account;
import app.data.request.UserDTO;
import app.data.response.type.Response;
import app.data.type.Production;
import app.mvc.repository.basement.Base_Repository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.apache.commons.lang3.RandomStringUtils;
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

    public Long user_info_update(User user, UserDTO.Update param) {

        JPAUpdateClause update = query.update(qUser).where(qUser.eq(user));

        if(param.getName() != null && !user.getName().equals(param.getName())){
            update.set(qUser.name, param.getName());
        }

        if(param.getEducation() != null && !user.getEducation().equals(param.getEducation())){
            update.set(qUser.education, param.getEducation());
        }

        if(param.getGender()!= null && !user.getGender().equals(param.getGender())){
            update.set(qUser.gender, param.getGender());
        }

        if(param.getYear() != null && param.getMonth() != null && param.getDate() != null && !new Birth(param.getYear(), param.getMonth(), param.getDate()).equals(user.getBirth())){
            update.set(qUser.birth.birth_1, param.getYear());
            update.set(qUser.birth.birth_2, param.getMonth());
            update.set(qUser.birth.birth_3, param.getDate());
        }


        if(!new Email(param.getEmailId(), param.getEmailAgency()).equals(user.getEmail())){

                update.set(qUser.email.email_1, param.getEmailId());
                update.set(qUser.email.email_2, param.getEmailAgency());

        }

        if(update.isEmpty()) return 0L;

        return update.execute();

    }

    public Optional<Password> password_get(String uuid) {

        Password  password = query.from(qUser_Account).select(qUser_Account.password).where(qUser_Account.user.uuid.eq(uuid)).fetchFirst();

        return (password != null) ? Optional.of(password) : Optional.empty();

    }

    public long user_password_update(String password, User user) {

        JPAUpdateClause update = query.update(qUser_Account).where(qUser_Account.user.eq(user));

        update.set(qUser_Account.password, new Password(password, RandomStringUtils.randomAlphanumeric(12)));

        return update.execute();

    }

    public UserDTO.Info_Result user_get_by_uuid(String uuid) {

        return query.from(qUser_Account)
                .select(Projections.constructor(UserDTO.Info_Result.class,
                        qUser_Account.id, qUser_Account.user.name, qUser_Account.user.gender, qUser_Account.user.birth,
                        qUser_Account.user.education, qUser_Account.user.email, qUser_Account.user.createTime, qUser_Account.user.updateTime
                ))
                .where(qUser.uuid.eq(uuid))
                .fetchFirst();

    }
}
