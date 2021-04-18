package tech.itpark.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Group {
    private long id;
    private long ownerId;
    private String name;
}
