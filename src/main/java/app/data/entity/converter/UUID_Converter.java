package app.data.entity.converter;

import app.utility.AES256;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UUID_Converter implements AttributeConverter<String, String> {

    @Autowired
    private AES256 aes;

    @Override
    public String convertToDatabaseColumn(String input) {
        return aes.decrypt(input);
    }

    @Override
    public String convertToEntityAttribute(String db) {
        return aes.encrypt(db);
    }

}
