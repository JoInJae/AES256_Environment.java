package app.data.entity;


import app.utils.AES256;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;


import javax.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity@Table(name = "User")
@Getter@NoArgsConstructor
public class User {

    @Column(name = "user_idx",  columnDefinition = "BIGINT", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idx;

    @Column(name = "user_unique",  columnDefinition = "VARCHAR(200)")
    @Convert(converter = UniqueConverter.class)
    private Unique unique;

    @Builder
    @AllArgsConstructor
    public static class Unique  implements Serializable {
        private String uuid;
        private String iv;
    }

    @Converter
    public static class UniqueConverter implements AttributeConverter<Unique, String>{

        @Override
        public String convertToDatabaseColumn(Unique input) {
            return new AES256().decrypt(input.uuid);

        }

        @Override
        public Unique convertToEntityAttribute(String db) {

            return new Unique("abc", db.substring(0,16));

        }


    }

    @PrePersist
    void preInsert(){
        this.unique = new Unique(new AES256().encrypt(UUID.randomUUID().toString().replaceAll("-","")), RandomStringUtils.randomNumeric(16).toString());
    }
}
