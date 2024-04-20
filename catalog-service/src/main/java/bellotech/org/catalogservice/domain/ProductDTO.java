package bellotech.org.catalogservice.domain;

import java.math.BigDecimal;

public record ProductDTO(String code, String name, String description, String imageUrl, BigDecimal price) {}
