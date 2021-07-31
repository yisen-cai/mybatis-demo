package com.glancebar.mybatis.cofig;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.glancebar.mybatis.enums.GenderEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author YISHEN CAI
 */
@Configuration
public class JacksonConfig {


    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        builder.serializerByType(Date.class, new SqlDateSerializer());
        builder.serializerByType(Timestamp.class, new SqlTimestampSerializer());
        builder.serializerByType(GenderEnum.class, new GenderEnumSerializer());
        return builder;
    }


    /**
     *
     */
    class SqlDateSerializer extends JsonSerializer<Date> {

        @Override
        public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeNumber(date.getTime());
        }
    }


    /**
     *
     */
    class GenderEnumSerializer extends JsonSerializer<GenderEnum> {

        @Override
        public void serialize(GenderEnum genderEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeNumber(genderEnum.getValue());
        }
    }

    /**
     *
     */
    class SqlTimestampSerializer extends JsonSerializer<Timestamp> {
        @Override
        public void serialize(Timestamp timestamp, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeNumber(timestamp.getTime());
        }
    }

}
