package tech.itpark.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.itpark.app.dto.GroupResponseDto;
import tech.itpark.app.dto.GroupSaveRequestDto;
import tech.itpark.app.dto.GroupSaveResponseDto;
import tech.itpark.app.exception.PermissionDeniedException;
import tech.itpark.app.mapper.GroupMapper;
import tech.itpark.app.model.Group;
import tech.itpark.app.repository.GroupRepository;
import tech.itpark.framework.security.Auth;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository repository;
    private final GroupMapper mapper;

    public List<GroupResponseDto> getAll() {
        return mapper.fromModelList(repository.getAll());
    }

    public GroupSaveResponseDto save(Auth auth, GroupSaveRequestDto dto) {
        if (auth.isAnonymous()) {
            throw new PermissionDeniedException();
        }
        final var toSave = mapper.fromDto(dto, auth.getId());
        final var saved = repository.save(toSave);
        return mapper.fromModel(saved);
    }
}
