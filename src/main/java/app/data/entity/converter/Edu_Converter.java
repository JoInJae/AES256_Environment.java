package app.data.entity.converter;

import app.data.type.Education;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter public class Edu_Converter implements AttributeConverter<Education, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Education input) {

        return input.getCode();

    }

    @Override
    public Education convertToEntityAttribute(Integer db) {

        return Education.get(db);

    }

}
