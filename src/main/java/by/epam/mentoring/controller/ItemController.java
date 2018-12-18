package by.epam.mentoring.controller;

import by.epam.mentoring.model.Item;
import by.epam.mentoring.model.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;

@Controller
public class ItemController {

    @RequestMapping(value = "/addItem", method = RequestMethod.POST)
    public String registration(@RequestParam("image") MultipartFile file, @ModelAttribute Item itemForm, BindingResult bindingResult) {
        // userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

//        userService.save(userForm);
//
//        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file) {
        return "redirect:/welcome";

    }
}
