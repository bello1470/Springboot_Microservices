package bellotech.org.catalogservice.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@Sql("/test-data.sql")
@DataJpaTest(
        properties = {
            "spring.test.database.replace=none",
            "spring.datasource.url=jdbc:tc:postgresql:16-alpine:///db",
        })
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldGetAllProducts() {

        List<ProductEntity> products = productRepository.findAll();
        assertThat(products).hasSize(12);
    }

    @Test
    void shouldGetProductByCode() {
        ProductEntity product = productRepository.findByCode("CODE1").orElseThrow();
        assertThat(product.getCode()).isEqualTo("CODE1");
        assertThat(product.getName()).isEqualTo("Product 1");
        assertThat(product.getDescription()).isEqualTo("Description of Product 1");
        assertThat(product.getPrice()).isEqualTo(new BigDecimal("10.99"));
    }

    @Test
    void shouldReturnEmptyWhenProductCodeNotExits() {

        assertThat(productRepository.findByCode("wrong_product_code")).isEmpty();
    }
}
