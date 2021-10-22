package app.data.request;

import app.data.entity.part.admin.Admin;
import app.data.entity.part.admin.Admin_Account;
import app.data.type.Production;
import lombok.*;
import java.time.LocalDate;
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

    @AllArgsConstructor
    @Builder
    @Getter
    @ToString
    public static class Main_Page_Result{

        private User user;
        private List<AdminDTO.Access> access;
        private List<AdminDTO.Basic> accumulations;
        private List<AdminDTO.TimeBy> timeBy;

    }

    @AllArgsConstructor
    @Getter
    public static class Basic{

        private String title;
        private Long number;

    }

    @AllArgsConstructor
    @Getter
    public static class User{

        private Long total;
        private Long new_user;
        private Long male;
        private Long female;

    }
    @AllArgsConstructor
    @Getter
    public static class Access{
        private LocalDate date;
        private Integer connection;
        private Integer new_user;
    }

    @AllArgsConstructor
    @Getter
    public static class TimeBy{
        private Integer hour;
        private String  name;
        private Integer run;
    }



}
