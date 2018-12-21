package by.epam.mentoring.service;

import by.epam.mentoring.dao.ItemDao;
import by.epam.mentoring.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ItemService {

    @Autowired
    private ItemDao itemDao;

    public Item save(Item item) {
        return itemDao.save(item);
    }

    public Collection<Item> getAll() {
        return itemDao.getAll();
    }

    public void delete(long id) {
        itemDao.delete(id);
    }

    public Item getById(long id) {
        return itemDao.getById(id);
    }
}
