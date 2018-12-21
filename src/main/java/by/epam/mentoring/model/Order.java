package by.epam.mentoring.model;

import by.epam.mentoring.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Order {

    private Long id;

    private OrderStatus orderStatus;

    private User user;

    private List<Item> items;

    public Order() {
        items =  new ArrayList<>();
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void removeItem(Item item){
        items.remove(item);
    }

}

