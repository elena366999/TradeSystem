package by.epam.mentoring.controller;

import by.epam.mentoring.model.Order;
import by.epam.mentoring.model.enums.OrderStatus;
import by.epam.mentoring.model.enums.Role;
import by.epam.mentoring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Collection;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/showAllOrders")
    public String showAllOrders(Model model) {
        Collection<Order> orders = orderService.getAll();
        model.addAttribute("orders", orders);
        model.addAttribute("statuses", Arrays.asList(OrderStatus.values()));
        return "somePage.html";

    }

    @PostMapping("/changeStatus/{id}")
    public String showAllOrders(Model model, @RequestParam("orderStatus") String status, @PathVariable("id") int id) {
    //    Order order = orderService.getById(id);
        orderService.update((long)id, OrderStatus.valueOf(status));
//        Collection<Order> orders = orderService.getAll();
//        model.addAttribute("orders", orders);
        return "redirect:/showAllOrders";

    }
}
