package hw.spring.common.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.sql.Date;

/**
 * Created by kamil on 26.04.17.
 */
public class CustomDateSerializer extends StdSerializer<Date> {

    public CustomDateSerializer() {
        this(null);
    }

    public CustomDateSerializer(Class<Date> t) {
        super(t);
    }

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String[] values = date.toString().split("-");
        final int year;
        final int month;
        final int day;
        year = Integer.parseInt(values[0]);
        month = Integer.parseInt(values[1]);
        day = Integer.parseInt(values[2]);
        jsonGenerator.writeArray(new int[]{year, month, day}, 0, 3);
    }
}
