package by.epam.mentoring.service;

import by.epam.mentoring.dao.OrderDao;
import by.epam.mentoring.model.Order;
import by.epam.mentoring.model.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    public void save(Order order) {
        orderDao.save(order);
    }

    public Collection<Order> getAll() {
        return orderDao.getAll();
    }

    public void delete(long id) {
        orderDao.delete(id);
    }

    public Order getById(long id) {
        return orderDao.getById(id);
    }

    public void update(Long id, OrderStatus orderStatus){
        orderDao.update(id, orderStatus);
    }
}
