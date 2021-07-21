package app.data.entity.basement;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public abstract class Entity_Log {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idx;

    @CreationTimestamp
    @Column(name = "create_time", columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime createTime;

}
