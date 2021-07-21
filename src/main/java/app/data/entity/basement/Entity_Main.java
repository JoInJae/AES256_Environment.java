package app.data.entity.basement;

import app.data.entity.converter.UUID_Converter;
import app.data.entity.listener.UUIDListener;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@EntityListeners(value = {UUIDListener.class})
@MappedSuperclass
@Getter
public abstract class Entity_Main {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idx;

    @Convert(converter = UUID_Converter.class)
    @Setter
    private String uuid;

    @CreationTimestamp
    @Column(name = "create_time", columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime createTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime updateTime;

}
