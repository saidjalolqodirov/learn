package uz.qodirov.unittest.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product create(ProductDto productDto) {
        log.info("Create product: {}", productDto);
        Product product = toModel(new Product(), productDto);
        return repository.save(product);
    }

    private Product toModel(Product product, ProductDto productDto) {
        product.setDescription(productDto.getDescription());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        return product;
    }

    @Override
    public Product update(Integer id, ProductDto productDto) {
        log.info("Update product: {}", productDto);
        Product product = repository.findById(id).orElse(null);
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }
        toModel(product, productDto);
        repository.save(product);
        return product;
    }

    @Override
    public void delete(Integer id) {
        log.info("Delete product: {}", id);
        repository.deleteById(id);
    }

    @Override
    public List<Product> findAll() {
        log.info("Find all products");
        return repository.findAll();
    }
}
