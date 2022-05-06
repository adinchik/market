package ru.geekbrains.march.market.cart.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.cart.services.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public CartDto getCurrentCart() {
        return cartService.getCurrentCart();
    }

    @GetMapping("/add/{productId}")
    public void addProductToCart(@PathVariable Long productId) {
        cartService.addToCart(productId);
    }

    @GetMapping("/clear")
    public void clear() {
        cartService.deleteAllItems();
    }

    @DeleteMapping("/{productId}")
    public void deleteProductFromCart(@PathVariable Long productId) {
        cartService.deleteFromCart(productId);
    }
}
