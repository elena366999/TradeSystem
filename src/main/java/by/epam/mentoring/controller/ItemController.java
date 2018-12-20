package by.epam.mentoring.controller;

import by.epam.mentoring.model.Item;
import by.epam.mentoring.model.Product;
import by.epam.mentoring.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/addItem", method = RequestMethod.POST)
    public String addItem(@RequestParam("image") MultipartFile file, @Valid @ModelAttribute Item itemForm, BindingResult bindingResult) {
        // userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        itemService.save(itemForm);
//
//        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "redirect:/admin";
    }

    @GetMapping("/deleteItem")
    public String deleteItem(@RequestParam("id") int id) {
        itemService.delete(id);
        return "redirect:/admin";

    }

    @GetMapping("/addItem")
    public String addItem(@RequestParam("id") int id) {
        Item item = itemService.getById(id);


        return "buy";

    }

    @RequestMapping(value = "/addItemToOrder/{productId}", method = RequestMethod.POST)
    public String addItemToOrder(@RequestParam("quantity") int quantity, @PathVariable("productId") Long productId) {

        return null;
    }


}
