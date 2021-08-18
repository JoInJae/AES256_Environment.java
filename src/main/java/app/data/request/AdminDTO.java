package app.data.request;

import app.data.entity.part.admin.Admin;
import app.data.entity.part.admin.Admin_Account;
import app.data.type.Production;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class AdminDTO {

    @AllArgsConstructor
    @Getter
    public static class Input{

        private final String id;
        private final String password;
        private final Production production;

        public Admin_Account to_entity(){

            Admin admin = Admin.builder().production(production).build();

            return Admin_Account.builder()
                    .admin(admin).id(id).password(password).build();
        }

    }

    @AllArgsConstructor
    @Getter
    public static class Login_Check{

        private final String id;
        private final String password;

    }


    @AllArgsConstructor
    @Builder
    @Getter
    public static class Login_Result{
        private String access;
    }

}
