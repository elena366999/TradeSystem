package by.epam.mentoring.validator;

import by.epam.mentoring.model.Product;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ItemValidator implements Validator {

//    @Autowired
//    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Product.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
//        User user = (User) o;
//
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");
//        if (user.getUsername().length() < 8 || user.getUsername().length() > 32) {
//            errors.rejectValue("username", "Size.userForm.username");
//        }
//
//        if (userService.findByUsername(user.getUsername()) != null) {
//            errors.rejectValue("username", "Duplicate.userForm.username");
//        }
//
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
//        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
//            errors.rejectValue("password", "Size.userForm.password");
//        }
//
//        if (!user.getConfirmPassword().equals(user.getPassword())) {
//            errors.rejectValue("confirmPassword", "Different.userForm.password");
//        }
    }
}

