package app.data.entity.user;

import app.data.entity.basement.Entity_Detail;

import com.google.common.hash.Hashing;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;


@Entity@Table(name = "USER_ACCOUNT")
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

    @Column(name = "user_password", columnDefinition = "CHAR(76)", nullable = false)
    private String hashing_password;

    @Transient
    private String password;

    @Column(name = "is_active", columnDefinition = "TINYINT(1)", nullable = false)
    private Long is_active;

    @Column(name = "token_refresh", columnDefinition = "LONGTEXT")
    private String refresh;

    @Column(name = "token_push", columnDefinition = "LONGTEXT")
    private String push;

    @Builder
    public User_Account(User user, String id, String password) {
        this.user = user;
        this.id = id;
        this.password = password;
    }

    @PrePersist
    void preInsert(){
        String salt = RandomStringUtils.randomAlphanumeric(12);
        this.hashing_password = Hashing.sha256().hashString(password + salt, StandardCharsets.UTF_8).toString() + salt;
        this.is_active = 1L;
    }

    public boolean match(String input){

        String origin_password, input_password, salt;

        origin_password = hashing_password.substring(0, hashing_password.length() - 12);
        salt = hashing_password.substring(hashing_password.length() - 12);
        input_password = Hashing.sha256().hashString(input + salt, StandardCharsets.UTF_8).toString();

        return origin_password.equals(input_password);

    }

}
