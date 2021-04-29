package tech.itpark.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import tech.itpark.app.dto.GroupSaveRequestDto;
import tech.itpark.app.service.GroupService;
import tech.itpark.framework.http.ContentTypes;
import tech.itpark.framework.http.ServerRequest;
import tech.itpark.framework.http.ServerResponse;

@Controller
@RequiredArgsConstructor
public class GroupController {
    private final GroupService service;

    public void save(ServerRequest request, ServerResponse response) {
        final var requestDto = request.read(GroupSaveRequestDto.class);
        final var responseDto = service.save(request.auth(), requestDto);
        response.write(responseDto, ContentTypes.APPLICATION_JSON);
    }

    public void remove(ServerRequest request, ServerResponse response) {
        final var requestDto = request.read(GroupSaveRequestDto.class);
        final var responseDto = service.save(request.auth(), requestDto);
        response.write(responseDto, ContentTypes.APPLICATION_JSON);
    }
}
