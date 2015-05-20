package ph.edu.emis.jobtracker.config;

import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import com.fasterxml.jackson.datatype.joda.ser.JacksonJodaFormat;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.joda.DateTimeFormatterFactory;
import ph.edu.emis.jobtracker.deserializers.CustomLocalDateTimeDeserializer;
import ph.edu.emis.jobtracker.serializers.CustomLocalDateTimeSerializer;

@Configuration
public class JacksonConfiguration {

    @Bean
    public JodaModule jacksonJodaModule() {
        JodaModule module = new JodaModule();
        DateTimeFormatterFactory formatterFactory = new DateTimeFormatterFactory();
        formatterFactory.setIso(DateTimeFormat.ISO.DATE);

        module.addDeserializer(LocalDateTime.class, new CustomLocalDateTimeDeserializer());
        module.addSerializer(LocalDateTime.class, new CustomLocalDateTimeSerializer());

        module.addSerializer(DateTime.class, new DateTimeSerializer(
                new JacksonJodaFormat(formatterFactory.createDateTimeFormatter()
                        .withZoneUTC())));
        return module;
    }
}
