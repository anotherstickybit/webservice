package tech.itpark.framework.bodyconverter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import tech.itpark.app.exception.ConversionException;

import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

@RequiredArgsConstructor
// option -> r -> i -> enter
public class StringBodyConverter implements BodyConverter {

  @Override
  public boolean canRead(String contentType, Class<?> clazz) {
    return clazz.isAssignableFrom(String.class);
  }

  @Override
  public boolean canWrite(String contentType, Class<?> clazz) {
    return clazz.isAssignableFrom(String.class);
  }

  @Override
  public <T> T read(HttpServletRequest request, Reader reader, Class<T> clazz) {
    try {
      final var writer = new StringWriter();
      reader.transferTo(writer);
      return (T) writer.toString();
    } catch (Exception e) {
      throw new ConversionException(e);
    }
  }

  // TODO: convert to unchecked exception
  @Override
  public void write(HttpServletResponse response, Writer writer, Object value) {
    try {
      writer.write((String) value);
    } catch (Exception e) {
      throw new ConversionException(e);
    }
  }
}
