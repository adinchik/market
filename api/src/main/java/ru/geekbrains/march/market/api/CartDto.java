package ru.geekbrains.march.market.api;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Модель корзины")
public class CartDto {
    @Schema(description = "Список продуктов в корзине", required = true)
    private List<CartItemDto> items;
    @Schema(description = "Общая сумма продуктов корзины", required = true, example = "15.0")
    private BigDecimal totalPrice;

    public List<CartItemDto> getItems() {
        return items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setItems(List<CartItemDto> items) {
        this.items = items;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
