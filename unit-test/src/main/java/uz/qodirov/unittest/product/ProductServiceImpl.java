package uz.qodirov.unittest.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    List<Product> products = new ArrayList<>();

    @Override
    public Product create(ProductDto productDto) {
        log.info("Create product: {}", productDto);
        Product product = toModel(productDto);
        product.setId(getId());
        products.add(product);
        return product;
    }

    private Integer getId() {
        if (products.isEmpty()) {
            return 1;
        }
        return products.stream().mapToInt(Product::getId).max().getAsInt() + 1;
    }

    private Product toModel(ProductDto productDto) {
        Product product = new Product();
        product.setDescription(productDto.getDescription());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        return product;
    }

    @Override
    public Product update(Integer id, ProductDto productDto) {
        log.info("Update product: {}", productDto);
        Product product = products.stream().filter((p) -> p.getId().equals(id)).findFirst().orElse(null);
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }
        toModel(productDto).setId(id);
        return product;
    }

    @Override
    public void delete(Integer id) {
        log.info("Delete product: {}", id);
        products.removeIf((p) -> p.getId().equals(id));
    }

    @Override
    public List<Product> findAll() {
        log.info("Find all products");
        return products;
    }
}
