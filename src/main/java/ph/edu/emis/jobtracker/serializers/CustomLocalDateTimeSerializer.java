package ph.edu.emis.jobtracker.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.LocalDateTime;

import java.io.IOException;

/**
 * Created by albertoclarit on 4/12/15.
 */
public class CustomLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {


        jsonGenerator.writeString(localDateTime.toString("yyyy-MM-dd'T'HH:mm:ss"));
    }
}
