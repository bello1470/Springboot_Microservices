package bellotech.org.catalogservice.domain;

public class ProductMapper {

    static ProductDTO toProduct(ProductEntity productEntity) {

        return new ProductDTO(
                productEntity.getDescription(),
                productEntity.getCode(),
                productEntity.getName(),
                productEntity.getImageUrl(),
                productEntity.getPrice());
    }
}
