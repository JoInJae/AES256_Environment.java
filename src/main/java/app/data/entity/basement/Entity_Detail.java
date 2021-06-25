package app.data.entity.basement;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@MappedSuperclass
@Getter@NoArgsConstructor
public abstract class Entity_Detail {

    @Id
    private Long idx;


}
