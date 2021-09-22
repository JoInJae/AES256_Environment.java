package app.data.request;

import app.data.entity.embeded.Birth;
import app.data.entity.embeded.Email;
import app.data.type.Gender;
import app.data.entity.part.user.User;
import app.data.entity.part.user.User_Account;
import app.data.type.Production;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserDTO {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Input{

        @Size(min = 1 , max = 30)
        @NotBlank
        private String id;

        @Size(min = 3)
        @NotBlank
        private String password;

        @NotBlank
        private String name;

        @NotNull
        private String year;

        @NotNull
        private String month;

        @NotNull
        private String date;

        private String emailId;

        private String emailAgency;

        @NotNull
        private Gender gender;

        @PositiveOrZero
        @NotNull
        private Long education;
        
        @NotNull
       private Production production;

        public User_Account to_entity(){

            User user = User.builder().name(name).year(year).month(month).date(date).email_id(emailId).email_agency(emailAgency).gender(gender).education(education).production(production).build();

            return User_Account.builder().user(user).id(id).password(password).build();

        }

    }

    @AllArgsConstructor
    @Getter
    public static class Update{

        private String name;
        private String year;
        private String month;
        private String date;
        private String emailId;
        private String emailAgency;
        private Gender gender;
        private Long education;

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

    @Getter
    public static class Info_Result{
        private final String id;
        private final String name;
        private final String gender;
        private final String birth;
        private final Long education;
        private final String email;
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;

        public Info_Result(String id, String name, Gender gender, Birth birth, Long education, Email email, LocalDateTime createdAt, LocalDateTime updatedAt) {
            this.id = id;
            this.name = name;
            this.gender = gender.getKor();
            this.birth = birth.getBirth_1() + birth.getBirth_2() + birth.getBirth_3();
            this.education = education;
            this.email = ( email != null ) ? email.getEmail_1() + "@" + email.getEmail_2() : null;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
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
    public static class Password{

        private String password;

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Login_Check{

        private String id;
        private String password;

    }

    @Getter
    public static class Login_Check_Result extends  UserDTO{

        private final String access;
        private final String refresh;

        @Builder
        public Login_Check_Result(String access, String refresh) {
            this.access = access;
            this.refresh = refresh;
        }

    }


    @Getter
    public static class Login_Check_WonJu_Result extends UserDTO{

        private final String access;
        private final String refresh;
        private final String name;
        private final String gender;
        private final Integer age;
        private final Long education;

        @Builder
        public Login_Check_WonJu_Result(String access, String refresh, String name, Gender gender, Birth birth, Long education) {
           LocalDate now = LocalDate.now();
            this.access = access;
            this.refresh = refresh;
            this.name = name;
            this.education = education;
            this.gender = gender.getKor();
            this.age = now.getYear() -  Integer.parseInt(birth.getBirth_1()) + 1;
        }

    }

    @AllArgsConstructor
    @Builder
    @Getter
    public static class Reissue_Result{
        private final String access;
    }

}
