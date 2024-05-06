package bellotech.org.orderservice.web.controller;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import bellotech.org.orderservice.AbstractIT;
import bellotech.org.orderservice.testdata.TestDataFactory;

import io.restassured.http.ContentType;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;


class OrderControllerTest extends AbstractIT {

    @Nested
    class CreateOrderTests {
        @Test
        void shouldCreateOrderSuccessfully() {

            mockGetProductByCode("CODE1", "Product 1", new BigDecimal("23.0"));
            var payload =
                    """
                                    {
                                        "customer": {
                                            "name": "bello",
                                            "email": "bello@gmail.com",
                                            "phone": "01234"
                                        },
                                                      
                                        "deliveryAddress": {
                                            "addressLine1": "st halens road",
                                            "addressLine2": "21A",
                                            "city": "swansea",
                                            "state": "Neath",
                                            "zipCode": "SA14AP",
                                            "country": "United Kingdom"
                                        },
                                        "items": [
                                            {
                                            "code": "CODE1",
                                            "name": "Product 1",
                                            "price": 23.0,
                                            "quantity": 1
                                            }
                                        ]
                                                      
                                    }
                            """;
            given().contentType(ContentType.JSON)
                  //  .header("Authorization", "Bearer " + getToken())
                    .body(payload)
                    .when()
                    .post("/api/orders")
                    .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .body("orderNumber", notNullValue());
        }

        @Test
        void shouldReturnBadRequestWhenMandatoryDataIsMissing() {
            var payload = TestDataFactory.createOrderRequestWithInvalidCustomer();
            given().contentType(ContentType.JSON)
                  //  .header("Authorization", "Bearer " + getToken())
                    .body(payload)
                    .when()
                    .post("/api/orders")
                    .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }
//
//    @Nested
//    class GetOrdersTests {
//        @Test
//        void shouldGetOrdersSuccessfully() {
//            List<OrderSummary> orderSummaries = given().when()
//                    .header("Authorization", "Bearer " + getToken())
//                    .get("/api/orders")
//                    .then()
//                    .statusCode(200)
//                    .extract()
//                    .body()
//                    .as(new TypeRef<>() {});
//
//            assertThat(orderSummaries).hasSize(2);
//        }
//    }
//
//    @Nested
//    class GetOrderByOrderNumberTests {
//        String orderNumber = "order-123";
//
//        @Test
//        void shouldGetOrderSuccessfully() {
//            given().when()
//                    .header("Authorization", "Bearer " + getToken())
//                    .get("/api/orders/{orderNumber}", orderNumber)
//                    .then()
//                    .statusCode(200)
//                    .body("orderNumber", is(orderNumber))
//                    .body("items.size()", is(2));
//        }
//    }
}
