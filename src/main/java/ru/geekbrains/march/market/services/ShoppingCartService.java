package ru.geekbrains.march.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.entities.Product;
import ru.geekbrains.march.market.entities.ShoppingCartItem;
import ru.geekbrains.march.market.repositories.ShoppingCartRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;

    public void addProduct(Product product){
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setProduct_id(product);
        shoppingCartRepository.save(shoppingCartItem);
    }

    public List<ShoppingCartItem> getAllItems() {
        return shoppingCartRepository.findAll();
    }
}
