package app.data.request;

import app.data.entity.part.admin.Admin;
import app.data.entity.part.admin.Admin_Account;
import app.data.type.Production;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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


    @NoArgsConstructor
    @Getter
    public static class Main_Page_Result{

        private final List<List<String>> gender = new ArrayList<>();
        private final List<List<String>> usage = new ArrayList<>();
        private final List<List<String>> time_sum = new ArrayList<>();
        private final List<List<String>> time_day = new ArrayList<>();

        @Setter
        private String total_member;
        @Setter
        private String new_member;

    }

}
