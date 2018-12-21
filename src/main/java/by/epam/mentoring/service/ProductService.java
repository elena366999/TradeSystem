package by.epam.mentoring.service;

import by.epam.mentoring.dao.ProductDao;
import by.epam.mentoring.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public void save(Product product) {
        productDao.save(product);
    }

    public Collection<Product> getAll() {
        return productDao.getAll();
    }

    public void delete(long id) {
        productDao.delete(id);
    }

    public Product getById(long id) {
        return productDao.getById(id);
    }

    public List<Product> getAllSortedByPrice() {
        List<Product> all = (List<Product> )getAll();
        all.sort(Comparator.comparing(Product::getPrice));
        return all;
    }
}
