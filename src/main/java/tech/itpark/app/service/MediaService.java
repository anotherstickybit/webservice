package tech.itpark.app.service;

import jakarta.servlet.http.Part;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import tech.itpark.app.dto.MediaSaveResponseDto;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

@Service
public class MediaService implements InitializingBean {
    private final Path path = Path.of("/Users/maratsadykov/tmp");

    public MediaSaveResponseDto save(Collection<Part> dto) {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Files.createDirectories(path);
    }
}
