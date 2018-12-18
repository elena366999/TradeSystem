package by.epam.mentoring.dao;

import by.epam.mentoring.model.Item;
import by.epam.mentoring.model.User;
import by.epam.mentoring.model.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.Collection;
import java.util.Objects;

public class ItemDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public Item findByUsername(String username) {
//        String sql = "SELECT * FROM users WHERE username=?";
//        return jdbcTemplate.queryForObject(sql, new Object[]{username}, new UserDao.UserMapper());
//    }

    public Collection<Item> getAll() {
        String sql = "SELECT * FROM items;";
        return jdbcTemplate.query(sql, new ItemDao.ItemMapper());
    }

    public User save(final User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO items (name, price, description, image, quantity) VALUES (?,?,?)";

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

    public void delete(final Item item) {
        String sql = "DELETE FROM items WHERE id = ?";
        jdbcTemplate.update(sql, item.getId());
//        String sql2 = "DELETE FROM t_ticket WHERE user_id = ?";
//        jdbcTemplate.update(sql2, user.getId());
    }

    public Item getById(final Long id) {
        String sql = "SELECT * FROM items WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ItemDao.ItemMapper());
    }

//    public User getByEmail(final String email) {
//        String sql = "SELECT * FROM users WHERE email=?";
//        return jdbcTemplate.queryForObject(sql, new Object[]{email}, new UserMapper());
//    }

    private static final class ItemMapper implements RowMapper<Item> {
        @Override
        public Item mapRow(final ResultSet resultSet, final int i) throws SQLException {
            Item item;

            item = new Item();
            item.setId((long) resultSet.getInt("id"));
            item.setDescription(resultSet.getString("description"));
            item.setName(resultSet.getString("name"));
            item.setPrice(resultSet.getDouble("price"));
            item.setQuantity(resultSet.getInt("quantity"));
            Blob image = resultSet.getBlob("image");
            image.getBytes(1L, (int)image.length());

            return item;
        }
    }
}
