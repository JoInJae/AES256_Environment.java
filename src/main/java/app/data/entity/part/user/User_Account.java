package app.data.entity.part.user;

import app.data.entity.basement.Entity_Detail;
import app.data.entity.embeded.Password;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import javax.persistence.*;

@Entity@Table(name = "User_Account")
@Getter@NoArgsConstructor
@AttributeOverrides({
        @AttributeOverride(name = "idx", column = @Column(name = "user_idx",  columnDefinition = "BIGINT", nullable = false)),
})
public class User_Account  extends Entity_Detail {

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_idx")
    private User user;

    @Column(name = "user_id", columnDefinition = "VARCHAR(30)", nullable = false, unique = true)
    private String id;

    @AttributeOverrides({
            @AttributeOverride(name = "hashing", column = @Column(name = "user_password", columnDefinition = "CHAR(76)", nullable = false))
    })
    @Embedded
    private Password password;

    @Column(name = "user_is_active", columnDefinition = "TINYINT(1)", nullable = false)
    private Long is_active;

    @Column(name = "user_token_refresh", columnDefinition = "LONGTEXT")
    private String refresh;

    @Column(name = "user_token_push", columnDefinition = "LONGTEXT")
    private String push;

    @Builder
    public User_Account(User user, String id, String password) {
        this.user = user;
        this.id = id;
        this.password = new Password(password, RandomStringUtils.randomAlphanumeric(12));
    }

    @PrePersist
    void preInsert(){
        this.is_active = 1L;
    }

}
