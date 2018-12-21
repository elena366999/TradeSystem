package by.epam.mentoring.controller;

import by.epam.mentoring.model.Product;
import by.epam.mentoring.service.ProductService;
import by.epam.mentoring.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.xml.validation.Validator;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductValidator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public String addProduct(Model model, @RequestParam("image") MultipartFile file, @ModelAttribute("productForm") Product productForm, BindingResult bindingResult) {
        validator.validate(productForm, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute( new Product());

            return "admin.jsp";
        }
        productForm.setImage(file);
        productService.save(productForm);

        return "redirect:/admin";
    }

    @GetMapping("/deleteProduct")
    public String deleteItem(@RequestParam("id") int id) {
        productService.delete(id);
        return "redirect:/admin";

    }

    @GetMapping("/buyProduct")
    public String buyProduct(@RequestParam("id") int id, Model model) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        return "buy.jsp";
    }
}
