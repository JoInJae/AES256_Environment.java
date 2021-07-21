package app.data.request;

import app.data.request.type.Gender;
import app.data.entity.User;
import app.data.entity.User_Account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class UserDTO {

    @Getter
    public static class Input{

        private String id;
        private String password;
        private String name;
        private String year;
        private String month;
        private String date;
        private Gender gender;
        private Long education;

        public User to_user(){
            return User.builder().name(name).year(year).month(month).date(date).gender(gender).education(education).build();
        }

        public User_Account to_user_account(User user){
            return User_Account.builder().user(user).id(id).password(password).build();
        }

    }

    @Getter
    public static class ID_Check{

        private String id;

    }

    @Getter
    public static class Login_Check{

        private String id;
        private String password;

    }


    @AllArgsConstructor
    @Builder
    @Getter
    public static class Login_Check_Result{
        private String access;
        private String refresh;
    }


    @AllArgsConstructor
    @Builder
    @Getter
    public static class Reissue_Result{
        private String access;
    }

}
