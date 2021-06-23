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
            return input.iv + new AES256().decrypt(input.uuid, input.iv);

        }

        @Override
        public Unique convertToEntityAttribute(String db) {

            return Unique.builder().uuid(new AES256().encrypt(db.substring(16), db.substring(0,16))).iv(db.substring(0,16)).build();

        }


    }

    @PrePersist
    void preInsert(){

        String iv = RandomStringUtils.randomAlphanumeric(16);

        this.unique = new Unique(new AES256().encrypt(UUID.randomUUID().toString().replaceAll("-",""), iv), iv);
    }
}
