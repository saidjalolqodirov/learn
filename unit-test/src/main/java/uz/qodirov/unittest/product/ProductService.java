package uz.qodirov.unittest.product;

import java.util.List;

public interface ProductService {
    Product create(ProductDto product);

    Product update(Integer id, ProductDto product);

    void delete(Integer id);

    List<Product> findAll();
}
