package app.data.request;

import app.data.entity.embeded.Birth;
import app.data.type.Education;
import app.data.type.Gender;
import app.data.entity.part.user.User;
import app.data.entity.part.user.User_Account;
import app.data.type.Production;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserDTO {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Input{

        private String id;
        private String password;
        private String name;
        private String year;
        private String month;
        private String date;
        private String emailId;
        private String emailAgency;
        private Gender gender;
        private Education education;
        private Production production;

        public User_Account to_entity(){

            User user = User.builder().name(name).year(year).month(month).date(date).email_id(emailId).email_agency(emailAgency).gender(gender).education(education).production(production).build();

            return User_Account.builder().user(user).id(id).password(password).build();
        }

    }

    @Getter
    public static class Simple_Info_Result{
        private final String id;
        private final String name;
        private final String gender;
        private final String birth;
        private final Long education;
        private final String production;

        public Simple_Info_Result(String id, String name, Gender gender, Birth birth, Long education, Production production) {
            this.id = id;
            this.name = name;
            this.gender = gender.getKor();
            this.birth = birth.getBirth_1() + birth.getBirth_2() + birth.getBirth_3();
            this.education = education;
            this.production = production.getName();
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class ID_Check{

        private String id;

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Login_Check{

        private String id;
        private String password;

    }

    @AllArgsConstructor
    @Builder
    @Getter
    public static class Login_Check_Result{
        private final String access;
        private final String refresh;
    }

    @AllArgsConstructor
    @Builder
    @Getter
    public static class Reissue_Result{
        private final String access;
    }

}
