package uz.qodirov.unittest.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository; // Mock repository

    @InjectMocks
    private ProductServiceImpl productService; // Repository ni inject qiladi

    @Test
    void testCreate() {
        ProductDto dto = new ProductDto("Laptop", "Powerful laptop", 2000.0);
        Product product = new Product("Laptop", "Powerful laptop", 2000.0);

        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product result = productService.create(dto);

        assertNotNull(result);
        assertEquals("Laptop", result.getName());
        assertEquals(2000.0, result.getPrice());

        verify(productRepository, times(1)).save(any(Product.class)); // `save()` chaqirilganini tekshirish
    }

    @Test
    void testUpdate_Success() {
        ProductDto dto = new ProductDto("Updated Laptop", "Updated Description", 2200.0);
        Product existingProduct = new Product("Laptop", "Powerful laptop", 2000.0);

        when(productRepository.findById(1)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(existingProduct);

        Product result = productService.update(1, dto);

        assertNotNull(result);
        assertEquals("Updated Laptop", result.getName());
        assertEquals(2200.0, result.getPrice());

        verify(productRepository, times(1)).findById(1);
        verify(productRepository, times(1)).save(existingProduct);
    }

    @Test
    void testUpdate_NotFound() {
        ProductDto dto = new ProductDto("Updated Laptop", "Updated Description", 2200.0);

        when(productRepository.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> productService.update(1, dto));

        assertEquals("Product not found", exception.getMessage());

        verify(productRepository, times(1)).findById(1);
        verify(productRepository, never()).save(any(Product.class)); // `save()` chaqirilmasligi kerak
    }

    @Test
    void testDelete() {
        doNothing().when(productRepository).deleteById(1);

        productService.delete(1);

        verify(productRepository, times(1)).deleteById(1);
    }

    @Test
    void testFindAll() {
        List<Product> productList = Arrays.asList(
                new Product("Laptop", "Powerful laptop", 2000.0),
                new Product("Phone", "Smartphone", 1000.0)
        );

        when(productRepository.findAll()).thenReturn(productList);

        List<Product> result = productService.findAll();

        assertEquals(2, result.size());
        assertEquals("Laptop", result.get(0).getName());
        assertEquals("Phone", result.get(1).getName());

        verify(productRepository, times(1)).findAll();
    }
}
