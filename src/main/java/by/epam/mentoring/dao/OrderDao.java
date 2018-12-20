package by.epam.mentoring.dao;

import by.epam.mentoring.model.Item;
import by.epam.mentoring.model.Order;
import by.epam.mentoring.model.enums.OrderStatus;
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
public class OrderDao {

    @Autowired
    private static JdbcTemplate jdbcTemplate;

    @Autowired
    private static UserDao userDao;
//    public Product findByUsername(String username) {
//        String sql = "SELECT * FROM users WHERE username=?";
//        return jdbcTemplate.queryForObject(sql, new Object[]{username}, new UserDao.UserMapper());
//    }

    public Collection<Order> getAll() {
        String sql = "SELECT * FROM orders inner join users on users.id;";
        return jdbcTemplate.query(sql, new OrderDao.OrderMapper());
    }

    public Order save(final Order order) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO orders (order_status, user_id) VALUES (?,?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, order.getOrderStatus().name());
            ps.setLong(2, order.getUser().getId());
            return ps;
        }, keyHolder);
        order.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        insertItemsInOrder(order);
        return order;
    }

    public void delete(final long id) {
        String sql = "DELETE FROM orders WHERE id = ?";
        String sql2 = "DELETE FROM order_items WHERE order_id = ?";

        jdbcTemplate.update(sql, id);
        jdbcTemplate.update(sql2, id);

//        String sql2 = "DELETE FROM t_ticket WHERE user_id = ?";
//        jdbcTemplate.update(sql2, user.getId());
    }

    public Order getById(final Long id) {
        String sql = "SELECT * FROM orders inner join order_items on orders.id WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new OrderDao.OrderMapper());
    }

//    public User getByEmail(final String email) {
//        String sql = "SELECT * FROM users WHERE email=?";
//        return jdbcTemplate.queryForObject(sql, new Object[]{email}, new UserMapper());
//    }

    private static final class OrderMapper implements RowMapper<Order> {
        @Override
        public Order mapRow(final ResultSet resultSet, final int i) throws SQLException {
            Order order;

            order = new Order();
            order.setId((long) resultSet.getInt("orders.id"));
            order.setOrderStatus(OrderStatus.valueOf(resultSet.getString("orders.order_status").toUpperCase()));
            order.setUser(userDao.getById(resultSet.getLong("orders.user_id")));
            order.setItems(getItemsByOrder(order.getId()));

            return order;
        }
    }

    private static List<Item> getItemsByOrder(final Long orderId) {
        String sql = "SELECT items.id, items.productId, items.quantity, products.id FROM items inner join order_items on order_items.item_id inner join products on products.id where order_items.order_id= ;" + orderId;
        return jdbcTemplate.query(sql, new ItemDao.ItemMapper());
    }

    private void insertItemsInOrder(final Order order) {
        String sql = "INSERT INTO order_items (order_id, item_id) VALUES (?,?)";
        List<Item> items = order.getItems();
        items.forEach(item -> jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setLong(1, order.getId());
            ps.setLong(2, item.getId());
            return ps;
        }));

    }
}
