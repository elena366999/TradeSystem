package by.epam.mentoring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Transient;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Long id;

    private String name;

    private double price;

    private String description;

    private MultipartFile image;

    @Transient
    private String encode;
}
