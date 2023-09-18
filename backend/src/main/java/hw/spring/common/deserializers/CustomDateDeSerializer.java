package hw.spring.common.deserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.sql.Date;

public class CustomDateDeSerializer extends JsonDeserializer {
    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        p.nextToken();
        int year = p.getIntValue();
        p.nextToken();
        int month = p.getIntValue();
        p.nextToken();
        int day = p.getIntValue();
        return new Date(year - 1900, month, day);
    }
}
