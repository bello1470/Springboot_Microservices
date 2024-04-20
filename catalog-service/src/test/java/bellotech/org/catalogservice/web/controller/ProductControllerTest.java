package bellotech.org.catalogservice.web.controller;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import bellotech.org.catalogservice.AbstractIT;
import bellotech.org.catalogservice.domain.ProductDTO;
import io.restassured.http.ContentType;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

@Sql("/test-data.sql")
class ProductControllerTest extends AbstractIT {

    @Test
    void shouldReturnProducts() {

        given().contentType(ContentType.JSON)
                .when()
                .get("/api/products")
                .then()
                .statusCode(200)
                .body("data", hasSize(10))
                .body("totalElements", is(12))
                .body("pageNumber", is(1))
                .body("totalPages", is(2))
                .body("isFirst", is(true))
                .body("isLast", is(false))
                .body("hasNext", is(true))
                .body("hasPrevious", is(false));
    }

    @Test
    void shouldGetProductByCode() {
        ProductDTO product = given().contentType(ContentType.JSON)
                .when()
                .get("/api/products/{code}", "CODE1")
                .then()
                .statusCode(200)
                .assertThat()
                .extract()
                .body()
                .as(ProductDTO.class);

        assertThat(product.code()).isEqualTo("CODE1");
        assertThat(product.name()).isEqualTo("Product 1");
        assertThat(product.description()).isEqualTo("Description of Product 1");
        assertThat(product.price()).isEqualTo(new BigDecimal("10.99"));
    }

    @Test
    void shouldReturnNotFoundWhenProductCodeNotExists() {
        String code = "wrong_product_code";
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/products/{code}", code)
                .then()
                .statusCode(404)
                .body("status", is(404))
                .body("title", is("Product Not Found"))
                .body("detail", is("Product with code " + code + " not found"));
    }
}
