package tech.itpark.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.itpark.app.dto.GroupSaveRequestDto;
import tech.itpark.app.dto.GroupSaveResponseDto;
import tech.itpark.app.exception.PermissionDeniedException;
import tech.itpark.app.model.Group;
import tech.itpark.app.repository.GroupRepository;
import tech.itpark.framework.security.Auth;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository repository;

    public GroupSaveResponseDto save(Auth auth, GroupSaveRequestDto dto) {
        if (auth.isAnonymous()) {
            throw new PermissionDeniedException();
        }
        final var saved = repository.save(new Group(
                dto.getId(),
                auth.getId(),
                dto.getName()
        ));
        return new GroupSaveResponseDto(
                saved.getId(),
                saved.getOwnerId(),
                saved.getName()
        );
    }
}
