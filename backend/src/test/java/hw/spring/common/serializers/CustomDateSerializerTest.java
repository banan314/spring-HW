package hw.spring.common.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalMatchers;

import java.io.IOException;
import java.sql.Date;

import static org.mockito.Mockito.*;

class CustomDateSerializerTest {
    CustomDateSerializer customDateSerializer;

    @BeforeEach
    void setUp() {
        customDateSerializer = new CustomDateSerializer();
    }

    @Disabled
    @Test
    void serializePassesDateNumbers() throws IOException {
        JsonGenerator jsonGenerator = mock(JsonGenerator.class);

        Date date = new Date(2003, 2, 1);
        customDateSerializer.serialize(date, jsonGenerator, null);

        verify(jsonGenerator, times(1))
                .writeArray(AdditionalMatchers.aryEq(new int[] { 1, 2, 2003 }), eq(0), eq(3));
    }
}