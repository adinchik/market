package ru.geekbrains.march.market.api;

import java.math.BigDecimal;
import java.util.List;


public class CartDto {
    private List<CartItemDto> items;
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
