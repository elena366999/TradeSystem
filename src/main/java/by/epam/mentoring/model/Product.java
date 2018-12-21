package by.epam.mentoring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
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
    @Size(min = 1, max = 100000)
    private double price;

    @NotNull
    @NotEmpty
    @NotBlank
    private String description;

    private MultipartFile image;

    private String encode;
}
