package by.epam.mentoring.dao;

import by.epam.mentoring.model.User;
import by.epam.mentoring.model.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username=?";
        List<User> query = jdbcTemplate.query(sql, new Object[]{username}, new UserMapper());
        if (query.isEmpty()){
            return null;
        }
        return query.get(0);
    }

    public Collection<User> getAll() {
        String sql = "SELECT * FROM users;";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    public User save(final User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO users (username, password, role) VALUES (?,?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole().name());

            return ps;
        }, keyHolder);
        user.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return user;
    }

    public void delete(final User user) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, user.getId());
//        String sql2 = "DELETE FROM t_ticket WHERE user_id = ?";
//        jdbcTemplate.update(sql2, user.getId());
    }

    public User getById(final Long id) {
        String sql = "SELECT * FROM users WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserMapper());
    }

//    public User getByEmail(final String email) {
//        String sql = "SELECT * FROM users WHERE email=?";
//        return jdbcTemplate.queryForObject(sql, new Object[]{email}, new UserMapper());
//    }

    private static final class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(final ResultSet resultSet, final int i) throws SQLException {
            User user;

            user = new User();
            user.setId((long) resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setRole(Role.valueOf(resultSet.getString("role").toUpperCase()));

            return user;
        }
    }
}
