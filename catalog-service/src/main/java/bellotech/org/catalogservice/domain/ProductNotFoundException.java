package bellotech.org.catalogservice.domain;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }

    public static ProductNotFoundException forCode(String code) {
        return new ProductNotFoundException("Product " + code + " not found");
    }
}
