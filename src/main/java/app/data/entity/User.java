package app.data.entity;

import app.data.entity.basement.Entity_Main;
import app.data.request.type.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity@Table(name = "USER")
@Getter@NoArgsConstructor
@AttributeOverrides({
        @AttributeOverride(name = "idx", column = @Column(name = "user_idx",  columnDefinition = "BIGINT", nullable = false)),
        @AttributeOverride(name = "uuid", column = @Column(name = "user_uuid",  columnDefinition = "VARCHAR(32)", nullable = false, unique = true))
})
public class User extends Entity_Main {

    @Column(name = "name", columnDefinition = "VARCHAR(10)", nullable = false)
    private String name;

    @AttributeOverrides({
            @AttributeOverride(name = "birth_1", column = @Column(name = "birth_year", columnDefinition = "CHAR(4)", nullable = false)),
            @AttributeOverride(name = "birth_2", column = @Column(name = "birth_month", columnDefinition = "CHAR(2)", nullable = false)),
            @AttributeOverride(name = "birth_3", column = @Column(name = "birth_date", columnDefinition = "CHAR(2)", nullable = false)),
    })
    @Embedded
    private Birth birth;

    @Column(name = "gender", columnDefinition = "CHAR(1)", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "education", columnDefinition = "INT(2)", nullable = false)
    private Long education;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Log> logs = new LinkedHashSet<>();

    @Builder
    public User(String name, Gender gender,String year, String month, String date, Long education) {
        this.name = name;
        this.birth = new Birth(year, month, date);
        this.gender = gender;
        this.education = education;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    @Getter
    public static class Birth{
        private String birth_1;
        private String birth_2;
        private String birth_3;
    }

}
