package tech.itpark.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GroupSaveResponseDto {
    private long id;
    private long ownerId;
    private String name;
}
