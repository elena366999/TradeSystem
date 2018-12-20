package by.epam.mentoring.controller;

import by.epam.mentoring.model.Item;
import by.epam.mentoring.model.Order;
import by.epam.mentoring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping( "/showAllOrders")
    public String showAllOrders(Model model) {
        Collection<Order> orders = orderService.getAll();
        model.addAttribute("orders", orders);

        return "orders";

    }
}
