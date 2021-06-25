package app.data.entity.basement;

import app.data.entity.converter.UUID_Converter;
import app.data.entity.listener.UUIDListener;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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



}
