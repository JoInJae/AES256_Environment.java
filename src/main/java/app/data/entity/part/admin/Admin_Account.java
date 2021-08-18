package app.data.entity.part.admin;

import app.data.entity.embeded.Password;
import app.data.entity.basement.Entity_Detail;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import javax.persistence.*;

@Entity@Table(name = "Admin_Account")
@Getter@NoArgsConstructor
@AttributeOverrides({
        @AttributeOverride(name = "idx", column = @Column(name = "admin_idx",  columnDefinition = "BIGINT", nullable = false)),
})
public class Admin_Account extends Entity_Detail {

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "admin_idx")
    private Admin admin;

    @Column(name = "admin_id", columnDefinition = "VARCHAR(30)", nullable = false, unique = true)
    private String id;

    @AttributeOverrides({
            @AttributeOverride(name = "hashing", column = @Column(name = "admin_password", columnDefinition = "CHAR(76)", nullable = false))
    })
    @Embedded
    private Password password;

    @Column(name = "admin_is_active", columnDefinition = "TINYINT(1)", nullable = false)
    private Long is_active;

    @Column(name = "admin_token_refresh", columnDefinition = "LONGTEXT")
    private String refresh;

    @Builder
    public Admin_Account(Admin admin, String id, String password) {
        this.admin = admin;
        this.id = id;
        this.password = new Password(password, RandomStringUtils.randomAlphanumeric(12));
    }

    @PrePersist
    void preInsert(){
        this.is_active = 1L;
    }


}
