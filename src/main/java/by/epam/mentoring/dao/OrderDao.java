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
import java.util.stream.Collectors;

@Repository
public class OrderDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserDao userDao;

//    public Product findByUsername(String username) {
//        String sql = "SELECT * FROM users WHERE username=?";
//        return jdbcTemplate.queryForObject(sql, new Object[]{username}, new UserDao.UserMapper());
//    }

    @Autowired
    private ItemDao itemDao;

    public Collection<Order> getAll() {
        String sql = "SELECT * FROM orders;";
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
        String sql = "SELECT * FROM orders WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new OrderDao.OrderMapper());
    }

//    public User getByEmail(final String email) {
//        String sql = "SELECT * FROM users WHERE email=?";
//        return jdbcTemplate.queryForObject(sql, new Object[]{email}, new UserMapper());
//    }

    private final class OrderMapper implements RowMapper<Order> {
        @Override
        public Order mapRow(final ResultSet resultSet, final int i) throws SQLException {
            Order order;
            long id = resultSet.getInt("id");
            order = new Order();
            order.setId(id);
            order.setOrderStatus(OrderStatus.valueOf(resultSet.getString("order_status").toUpperCase()));
            order.setUser(userDao.getById(resultSet.getLong("user_id")));
            order.setItems(getItemsByOrder(id));

            return order;
        }
    }

    private List<Item> getItemsByOrder(final Long orderId) {
        String sql = "SELECT item_id from order_items where order_id= " + orderId;
        List<Long> list = jdbcTemplate.queryForList(sql, Long.class);
        return list.stream().map(id -> itemDao.getById(id)).collect(Collectors.toList());
    }

    public void update(Long id, OrderStatus orderStatus){
        String SQL = "update orders set order_status = ? where id = ?";
        jdbcTemplate.update(SQL, orderStatus.name(), id);
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
