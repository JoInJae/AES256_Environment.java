package app.mvc.repository;

import app.data.entity.part.admin.Admin_Account;
import app.data.entity.part.admin.QAdmin;
import app.data.entity.part.admin.QAdmin_Account;
import app.data.entity.part.user.QUser;
import app.data.request.AdminDTO;
import app.data.type.Production;
import app.mvc.repository.basement.Base_Repository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class Admin_Custom_Repository extends Base_Repository {

    QAdmin_Account q_admin_account = QAdmin_Account.admin_Account;
    QAdmin q_admin = QAdmin.admin;
    QUser q_user = QUser.user;


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

    public AdminDTO.Main_Page_Result main_info_get(Production production) {

        AdminDTO.Main_Page_Result result = new AdminDTO.Main_Page_Result();


        return null;

    }

}
