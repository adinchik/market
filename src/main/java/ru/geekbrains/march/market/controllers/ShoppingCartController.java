package ru.geekbrains.march.market.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.entities.Product;
import ru.geekbrains.march.market.entities.ShoppingCartItem;
import ru.geekbrains.march.market.services.ShoppingCartService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shoppingCart")
@RequiredArgsConstructor
public class ShoppingCartController {
    public final ShoppingCartService shoppingCartService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void addProductToShoppingCart(@RequestBody Product product) {
        //System.out.println(createNewShoppingCartItemDto.getProduct().getTitle());
        shoppingCartService.addProduct(product);
    }

    @GetMapping
    public List<ShoppingCartItem> getAllItemsInShoppingCart() {
        return shoppingCartService.getAllItems();
    }

}
