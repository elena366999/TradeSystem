package by.epam.mentoring.model;

import by.epam.mentoring.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Long id;

    private OrderStatus orderStatus;

    private User user;

    private List<Item> items;


}

