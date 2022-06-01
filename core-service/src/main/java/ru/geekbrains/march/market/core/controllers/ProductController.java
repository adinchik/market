package ru.geekbrains.march.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.api.PageDto;
import ru.geekbrains.march.market.api.ProductDto;
import ru.geekbrains.march.market.core.converters.PageConverter;
import ru.geekbrains.march.market.core.converters.ProductConverter;
import ru.geekbrains.march.market.core.exceptions.ResourceNotFoundException;
import ru.geekbrains.march.market.core.entities.Product;
import ru.geekbrains.march.market.core.repositories.specofocations.ProductsSpecifications;
import ru.geekbrains.march.market.core.services.ProductService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;
    private final PageConverter pageConverter;

    @GetMapping
    public PageDto getAllProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "page_size", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "title_part", required = false) String titlePart,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice
    ) {
        if (page < 1) {
            page = 1;
        }
        Specification<Product> spec = Specification.where(null);
        if (titlePart != null) {
            spec = spec.and(ProductsSpecifications.titleLike(titlePart));
        }
        if (minPrice != null) {
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThan(BigDecimal.valueOf(minPrice)));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductsSpecifications.priceLessThanOrEqualsThan(BigDecimal.valueOf(maxPrice)));
        }
        Page<ProductDto> pageProducts = productService.findAll(page - 1, pageSize, spec).map(productConverter::entityToDto);
        return pageConverter.entityToDto(pageProducts);

    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
//        Optional<Product> p = productService.findById(id);
//        if (p.isPresent()) {
//            return new ResponseEntity<>(productConverter.entityToDto(p.get()), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(new AppError("RESOURCE_NOT_FOUND", "Продукт с id: " + id + " не найден"), HttpStatus.NOT_FOUND);
        return productConverter.entityToDto(productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт с id: " + id + " не найден")));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewProducts(@RequestBody ProductDto productDto) {
        productService.createNewProduct(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
