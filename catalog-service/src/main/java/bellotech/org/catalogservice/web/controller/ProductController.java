package bellotech.org.catalogservice.web.controller;

import bellotech.org.catalogservice.domain.PageResult;
import bellotech.org.catalogservice.domain.ProductDTO;
import bellotech.org.catalogservice.domain.ProductNotFoundException;
import bellotech.org.catalogservice.domain.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    PageResult<ProductDTO> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo) {

        return productService.getProducts(pageNo);
    }

    @GetMapping("/{code}")
    ResponseEntity<ProductDTO> getProductByCode(@PathVariable String code) {

        return productService
                .getProductByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> ProductNotFoundException.forCode(code));
    }
}
