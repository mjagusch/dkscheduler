package org.autumnridge.disciplekids.util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

@Component("arcObjectMapper")
public class ArcObjectMapper extends ObjectMapper {
	
	private static final JsonSerializer<LocalDate> jsonLocalDateSerializer = new JsonSerializer<LocalDate>() {
		public void serialize(LocalDate value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {				
			jgen.writeString(value.toString("MM/dd/yyyy"));
		}
	};
	
	public static final ObjectMapper INSTANCE = new ArcObjectMapper(); 
	
	public ArcObjectMapper(){
		super();
		CustomSerializerFactory serializer = new CustomSerializerFactory();
		serializer.addGenericMapping(LocalDate.class, jsonLocalDateSerializer);
		this.setSerializerFactory(serializer);
		this.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
		this.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
		this.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
}
