package by.epam.mentoring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Long id;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 4, max = 100, message = "Product name should contain between {min} and {max} symbols")
    private String name;
    @NotNull
//    @Digits(integer = 10 , fraction = 2 )

    private double price;
    @NotNull
    @NotEmpty
    @NotBlank
    private String description;

    private MultipartFile image;

    @Transient
    private String encode;
}
