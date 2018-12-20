package by.epam.mentoring.dao;

import by.epam.mentoring.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.*;
import java.util.Base64;
import java.util.Collection;
import java.util.Objects;

@Repository
public class ProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public Product findByUsername(String username) {
//        String sql = "SELECT * FROM users WHERE username=?";
//        return jdbcTemplate.queryForObject(sql, new Object[]{username}, new UserDao.UserMapper());
//    }

    public Collection<Product> getAll() {
        String sql = "SELECT * FROM products;";
        return jdbcTemplate.query(sql, new ProductDao.ProductMapper());
    }

    public Product save(final Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO products (name, description, price, image) VALUES (?,?,?,?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            if (product.getImage() != null) {
                try {
                    ps.setBlob(4, product.getImage().getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                ps.setBlob(4, (Blob) null);
            }

            return ps;
        }, keyHolder);
        product.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return product;
    }

    public void delete(final long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(sql, id);
//        String sql2 = "DELETE FROM t_ticket WHERE user_id = ?";
//        jdbcTemplate.update(sql2, user.getId());
    }

    public Product getById(final Long id) {
        String sql = "SELECT * FROM products WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ProductDao.ProductMapper());
    }

//    public User getByEmail(final String email) {
//        String sql = "SELECT * FROM users WHERE email=?";
//        return jdbcTemplate.queryForObject(sql, new Object[]{email}, new UserMapper());
//    }

    private static final class ProductMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(final ResultSet resultSet, final int i) throws SQLException {
            Product product;

            product = new Product();
            product.setId((long) resultSet.getInt("id"));
            product.setDescription(resultSet.getString("description"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getDouble("price"));
            Blob image = resultSet.getBlob("image");
            if (image != null) {
                byte[] bytes = image.getBytes(1L, (int) image.length());
                String encode = Base64.getEncoder().encodeToString(bytes);
                product.setEncode(encode);
            }
            return product;
        }
    }
}
