package microteam.springdatajpa.repository;

import microteam.springdatajpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);
    List<Product> findByCategory(Locale.Category category);
    List<Product> findByCategoryAndName(Locale.Category category, String name);
    List<Product> findByDateCreatedBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Product> findByPriceBetween(BigDecimal startPrice, BigDecimal endPrice);

}
