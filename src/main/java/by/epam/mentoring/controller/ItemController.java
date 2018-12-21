package by.epam.mentoring.controller;

import by.epam.mentoring.model.Item;
import by.epam.mentoring.model.Order;
import by.epam.mentoring.model.Product;
import by.epam.mentoring.model.User;
import by.epam.mentoring.model.enums.OrderStatus;
import by.epam.mentoring.service.ItemService;
import by.epam.mentoring.service.OrderService;
import by.epam.mentoring.service.ProductService;
import by.epam.mentoring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/addItem", method = RequestMethod.POST)
    public String addItem(@RequestParam("image") MultipartFile file, @Valid @ModelAttribute Item itemForm, BindingResult bindingResult) {
        // userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration.jsp";
        }

        itemService.save(itemForm);
//
//        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "redirect:/admin.jsp";
    }

    @GetMapping("/deleteItem")
    public String deleteItem(@RequestParam("id") int id) {
        itemService.delete(id);
        return "redirect:/admin.jsp";

    }

    @GetMapping("/addItem")
    public String addItem(@RequestParam("id") int id) {
        Item item = itemService.getById(id);

        return "buy.jsp";

    }

    @RequestMapping(value = "/addItemToOrder/{productId}", method = RequestMethod.POST)
    public String addItemToOrder(@RequestParam("quantity") int quantity, @PathVariable("productId") Long productId, Principal user) {
        Item item = new Item();
        item.setQuantity(quantity);
        item.setProduct(productService.getById(productId));
        Item savedItem = itemService.save(item);

        User userFromDb = userService.findByUsername(user.getName());
        List<Order> ordersForUser = orderService.getOrdersForUser(userFromDb.getId());
        List<Order> newOrders = new ArrayList<>();
        if (ordersForUser != null && !ordersForUser.isEmpty()) {
            newOrders = ordersForUser.stream().filter(order -> order.getOrderStatus().equals(OrderStatus.NEW)).collect(Collectors.toList());
        }
        if (newOrders != null && !newOrders.isEmpty()) {
            Order order = newOrders.get(0);
            order.addItem(item);
            orderService.addItemToOrder(order.getId(), savedItem.getId());
        } else {
            Order order = new Order();
            order.setOrderStatus(OrderStatus.NEW);
            order.setUser(userFromDb);
            order.addItem(item);
            orderService.save(order);
        }
        return "redirect:/welcome";
    }


}
