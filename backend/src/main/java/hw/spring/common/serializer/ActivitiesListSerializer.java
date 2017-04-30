package hw.spring.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import hw.spring.model.Activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kamil on 26.04.17.
 */
public class ActivitiesListSerializer extends StdSerializer<List<Activity>> {

    public ActivitiesListSerializer() {
        this(null);
    }

    public ActivitiesListSerializer(Class<List<Activity>> t) {
        super(t);
    }

    @Override
    public void serialize(List<Activity> activities, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        int size = activities.size();
        ArrayList<String> activitiesNames = new ArrayList<>();
        activities.stream().forEach(a -> {
            String name = a.getName();
            activitiesNames.add(name);
        });
        jsonGenerator.writeStartArray();
        activities.stream().forEach(a -> {
            try {
                jsonGenerator.writeString(a.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        jsonGenerator.writeEndArray();
    }
}
