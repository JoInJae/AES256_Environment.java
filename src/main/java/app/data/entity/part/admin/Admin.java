package app.data.entity.part.admin;

import app.data.entity.basement.Entity_Main;
import app.data.type.Production;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity@Table(name = "Admin")
@Getter@NoArgsConstructor
@AttributeOverrides({
        @AttributeOverride(name = "idx", column = @Column(name = "admin_idx",  columnDefinition = "BIGINT", nullable = false)),
        @AttributeOverride(name = "uuid", column = @Column(name = "admin_uuid",  columnDefinition = "VARCHAR(32)", nullable = false, unique = true))
})
public class Admin extends Entity_Main {

    @Column(name = "production", columnDefinition = "VARCHAR(10)", nullable = false)
    @Enumerated(EnumType.STRING)
    private Production production;

    @Builder
    public Admin(Production production) {
        this.production = production;
    }

}
