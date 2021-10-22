package app.mvc.repository;

import app.data.entity.part.admin.Admin_Account;
import app.data.entity.part.admin.QAdmin_Account;
import app.data.entity.part.admin.QNew_Connect;
import app.data.entity.part.admin.QTime_Contents;
import app.data.entity.part.user.QUser;
import app.data.request.AdminDTO;
import app.data.type.Gender;
import app.data.type.Production;
import app.mvc.repository.basement.Base_Repository;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class Admin_Custom_Repository extends Base_Repository {

    QAdmin_Account q_admin_account = QAdmin_Account.admin_Account;
    QUser q_user = QUser.user;
    QNew_Connect q_new_connect = QNew_Connect.new_Connect;
    QTime_Contents q_time_contents = QTime_Contents.time_Contents;

    protected Admin_Custom_Repository(JPAQueryFactory query) {
        super(query);
    }

    public Optional<Admin_Account> account_get_by_id(String id){

        Admin_Account admin_account = query.selectFrom(q_admin_account).where(q_admin_account.id.eq(id)).fetchFirst();

        return (admin_account != null) ? Optional.of(admin_account) : Optional.empty();

    }

    public Optional<Admin_Account> account_get_by_uuid(String uuid){

        Admin_Account admin_account = query.selectFrom(q_admin_account).where(q_admin_account.admin.uuid.eq(uuid)).fetchFirst();

        return (admin_account != null) ? Optional.of(admin_account) : Optional.empty();

    }

    public long token_update(Admin_Account admin_account, String refresh){

        return query.update(q_admin_account).set(q_admin_account.refresh, refresh).where(q_admin_account.eq(admin_account)).execute();

    }

    public AdminDTO.User get_user_info() {

        LocalDate now = LocalDate.now();

        return query.from(q_user).select(
                Projections.constructor(AdminDTO.User.class,
                        q_user.count().longValue(),
                        new CaseBuilder()
                                .when(q_user.createTime.year().eq(now.getYear()).and(q_user.createTime.month().eq(now.getMonth().getValue())).and(q_user.createTime.dayOfMonth().eq(now.getDayOfMonth())))
                                .then(1L).otherwise(0L).sum(),
                        new CaseBuilder()
                                .when(q_user.gender.eq(Gender.M)).then(1L).otherwise(0L).sum(),
                        new CaseBuilder()
                                .when(q_user.gender.eq(Gender.F)).then(1L).otherwise(0L).sum()
                        ))
                .where(q_user.production.eq(Production.XR))
                .fetchOne();

    }


    public List<AdminDTO.Access> get_access_info() {

        return query.select(
                Projections.constructor(AdminDTO.Access.class,
                        q_new_connect.nac_date,
                        q_new_connect.nac_connection,
                        q_new_connect.nac_new
                ))
                .from(q_new_connect).fetch();

    }

    public List<AdminDTO.Basic> get_time_sum_info() {
        return query.select(Projections.constructor(AdminDTO.Basic.class,
                        q_time_contents.tbc_content,
                        q_time_contents.tbc_runtime.sum().longValue()
                ))
                .from(q_time_contents)
                .groupBy(q_time_contents.tbc_content).fetch();
    }

    public List<AdminDTO.TimeBy> get_time_by_info() {
        return query.select(Projections.constructor(AdminDTO.TimeBy.class,
                        q_time_contents.tbc_hour,
                        q_time_contents.tbc_content,
                        q_time_contents.tbc_runtime
                ))
                .from(q_time_contents)
                .fetch();
    }

}
