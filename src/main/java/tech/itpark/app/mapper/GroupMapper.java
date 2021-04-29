package tech.itpark.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.itpark.app.dto.GroupResponseDto;
import tech.itpark.app.dto.GroupSaveRequestDto;
import tech.itpark.app.dto.GroupSaveResponseDto;
import tech.itpark.app.model.Group;

import java.util.List;

@Mapper
public interface GroupMapper {
//    @Mapping(target = "ownerId", defaultValue = "0")
    Group fromDto(GroupSaveRequestDto dto, long ownerId);
    GroupSaveResponseDto fromModel(Group model);
    List<GroupResponseDto> fromModelList(List<Group> list);
}
