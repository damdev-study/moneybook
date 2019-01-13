package api.damdev.moneybook.common.errors;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.validation.Errors;

/**
 * Author : zenic
 * Created : 2019-01-13
 */
@JsonComponent
public class ErrorsSerializer extends JsonSerializer<Errors> {

  @Override
  public void serialize(Errors errors, JsonGenerator gen,
    SerializerProvider serializerProvider) throws IOException {
    gen.writeStartArray();
    errors.getFieldErrors().stream().forEach(e -> {
      try {
        gen.writeStartObject();
        gen.writeStringField("field", e.getField());
        gen.writeStringField("objectName", e.getObjectName());
        gen.writeStringField("code", e.getCode());
        gen.writeStringField("defaultMessage", e.getDefaultMessage());
        Object rejectValue = e.getRejectedValue();
        if (rejectValue != null) {
          gen.writeStringField("rejectValue", rejectValue.toString());
        }
        gen.writeEndObject();
      } catch (IOException e1) {
        e1.printStackTrace();
      }
    });

    errors.getGlobalErrors().stream().forEach(e -> {
      try {
        gen.writeStartObject();
        gen.writeStringField("objectName", e.getObjectName());
        gen.writeStringField("code", e.getCode());
        gen.writeStringField("defaultMessage", e.getDefaultMessage());
        gen.writeEndObject();
      } catch (IOException e1) {
        e1.printStackTrace();
      }
    });
    gen.writeEndArray();
  }
}
