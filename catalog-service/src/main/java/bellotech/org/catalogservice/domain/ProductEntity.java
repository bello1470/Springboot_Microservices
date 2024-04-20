package bellotech.org.catalogservice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_generator")
    @SequenceGenerator(name = "product_id_generator", sequenceName = "product_id_seq")
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Product code is required")
    private String code;

    @Column(nullable = false)
    @NotBlank(message = "Product name is required")
    private String name;

    private String description;

    private String imageUrl;

    @Column(nullable = false)
    @NotBlank(message = "Product price is required")
    private BigDecimal price;
}
