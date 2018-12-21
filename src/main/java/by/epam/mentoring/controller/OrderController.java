package by.epam.mentoring.controller;

import by.epam.mentoring.model.Order;
import by.epam.mentoring.model.User;
import by.epam.mentoring.model.enums.OrderStatus;
import by.epam.mentoring.service.OrderService;
import by.epam.mentoring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("/showAllOrders")
    public String showAllOrders(Model model) {
        Collection<Order> orders = orderService.getAll();
        model.addAttribute("orders", orders);
        model.addAttribute("statuses", Arrays.asList(OrderStatus.values()));
        return "orders.html";

    }

    @PostMapping("/changeStatus/{id}")
    public String showAllOrders(Model model, @RequestParam("orderStatus") String status, @PathVariable("id") int id) {
        orderService.update((long) id, OrderStatus.valueOf(status));
        return "redirect:/showAllOrders";

    }

    @GetMapping("/viewMyPurchases")
    public String viewMyPurchases(Model model, Principal user) {
        User userFromDb = userService.findByUsername(user.getName());
                Collection<Order> orders = orderService.getOrdersForUser(userFromDb.getId());
        model.addAttribute("orders", orders);
        return "userOrders.html";
    }
}
