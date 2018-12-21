package by.epam.mentoring.dao;

import by.epam.mentoring.model.Item;
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
import java.util.Objects;

@Repository
public class ItemDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProductDao productDao;

//    public Product findByUsername(String username) {
//        String sql = "SELECT * FROM users WHERE username=?";
//        return jdbcTemplate.queryForObject(sql, new Object[]{username}, new UserDao.UserMapper());
//    }

    public Collection<Item> getAll() {
        String sql = "SELECT * FROM items INNER JOIN products ON products.id;";
        return jdbcTemplate.query(sql, new ItemDao.ItemMapper());
    }

    public Item save(final Item item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO items (product_id, quantity) VALUES (?,?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setLong(1, item.getProduct().getId());
            ps.setInt(2, item.getQuantity());
            return ps;
        }, keyHolder);
        item.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return item;
    }

    public void delete(final long id) {
        String sql = "DELETE FROM items WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Item getById(final Long id) {
        String sql = "SELECT * FROM items WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ItemDao.ItemMapper());
    }

    final class ItemMapper implements RowMapper<Item> {
        @Override
        public Item mapRow(final ResultSet resultSet, final int i) throws SQLException {
            Item item;

            item = new Item();
            item.setId((long) resultSet.getInt("items.id"));
            item.setProduct(productDao.getById(resultSet.getLong("items.product_id")));
            item.setQuantity(resultSet.getInt("items.quantity"));

            return item;
        }
    }
}
