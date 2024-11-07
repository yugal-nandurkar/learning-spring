package microteam.springdatajpa.repository;

import microteam.springdatajpa.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DataJpaTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveMethod(){
        Product product = new Product();
        product.setName("product 1");
        product.setDescription("product 1 description");
        product.setSku("100ABC");
        product.setPrice(new BigDecimal("100.00"));
        product.setActive(true);
        product.setImageUrl("product1.png");

        Product savedObject = productRepository.save(product);

        System.out.println(savedObject.getId());
        System.out.println(savedObject.toString());
    }

    @Test
    void updateUsingSaveMethod(){
        Long id = 1L;
        Product product = productRepository.findById(id).get();
        product.setName("updated product 1");
        product.setDescription("updated product 1 description");
        productRepository.save(product);
    }

    @Test
    void findByIdMethod(){
        Long id = 1L;
        Product product = productRepository.findById(id).get();
    }

    @Test
    void saveAllMethod(){
        Product product_2 = new Product();
        product_2.setName("product 2");
        product_2.setDescription("product 2 description");
        product_2.setSku("100XYZ");
        product_2.setPrice(new BigDecimal("200.00"));
        product_2.setActive(true);
        product_2.setImageUrl("product2.png");

        Product product_3 = new Product();
        product_3.setName("product 3");
        product_3.setDescription("product 3 description");
        product_3.setSku("100MNO");
        product_3.setPrice(new BigDecimal("300.00"));
        product_3.setActive(true);
        product_3.setImageUrl("product3.png");
        productRepository.saveAll(List.of(product_2, product_3));
    }

    @Test
    void findAllMethod(){
        List<Product> products = productRepository.findAll();
        products.forEach((product) -> {
            System.out.println(product.getName());
        });
    }

    @Test
    void deleteByIdMethod(){
        Long id = 1L;
        productRepository.deleteById(id);
    }

    @Test
    void deleteMethod(){
        Long id = 2L;
        Product product = productRepository.findById(id).get();
        productRepository.delete(product);
    }

    @Test
    void deleteAllMethod(){
        productRepository.deleteAll();
    }

    @Test
    void countMethod(){
        Long count = productRepository.count();
        System.out.println(count);
    }

    @Test
    void existsByIdMethod(){
        Boolean exists = productRepository.existsById(1L);
        System.out.println(exists);
    }
}