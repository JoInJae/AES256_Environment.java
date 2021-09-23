package app.data.entity.converter;

import app.data.type.Wonju;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class Wonju_Converter implements AttributeConverter<Wonju, String> {

    @Override
    public String convertToDatabaseColumn(Wonju input) {

        return input.getCode();

    }

    @Override
    public Wonju convertToEntityAttribute(String db) {

        return (db != null) ? Wonju.get(db) : null;

    }

}
