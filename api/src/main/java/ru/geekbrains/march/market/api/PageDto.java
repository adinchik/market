package ru.geekbrains.march.market.api;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Модель Page")
public class PageDto {
    @Schema(description = "Список продуктов", required = true)
    private List<ProductDto> products;
    @Schema(description = "Количество страниц", required = true, example = "3")
    private int totalPages;
    @Schema(description = "Количество продуктов", required = true, example = "12")
    private int totalElements;

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }
}
