package by.epam.mentoring.controller;

import by.epam.mentoring.model.Item;
import by.epam.mentoring.model.Product;
import by.epam.mentoring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public String addItem(@RequestParam("image") MultipartFile file, @ModelAttribute Product productForm, BindingResult bindingResult) {
        // userValidator.validate(userForm, bindingResult);


        if (bindingResult.hasErrors()) {
            return "admin.jsp";
        }

        productForm.setImage(file);

        productService.save(productForm);
//
//        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "redirect:/admin.jsp";
    }

    @GetMapping("/deleteProduct")
    public String deleteItem(@RequestParam("id") int id) {
        productService.delete(id);
        return "redirect:/admin.jsp";

    }

    @GetMapping("/buyProduct")
    public String buyProduct(@RequestParam("id") int id, Model model) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        return "buy.jsp";
    }
}
