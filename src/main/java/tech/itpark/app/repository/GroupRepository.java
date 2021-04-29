package tech.itpark.app.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import tech.itpark.app.model.Group;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class GroupRepository {
    private final NamedParameterJdbcOperations jdbc;

    public GroupRepository(DataSource ds) {
        jdbc = new NamedParameterJdbcTemplate(ds);
    }

    public List<Group> getAll() {
        return jdbc.query(
                "select id, ownerId, name from groups order by id",
                ((rs, rowNum) -> new Group(
                        rs.getLong("id"),
                        rs.getLong("ownerId"),
                        rs.getString("name")
                ))
        );
    }

    public Group save(Group group) {
        final var id = jdbc.queryForObject(
                "insert into groups(ownerid, name) values (:ownerId, :name) returning id",
                Map.of(
                        "ownerId", group.getOwnerId(),
                        "name", group.getName()
                ),
                (rs, rowNum) -> rs.getLong("id")
        );
        group.setId(id);
        return group;
    }
}
