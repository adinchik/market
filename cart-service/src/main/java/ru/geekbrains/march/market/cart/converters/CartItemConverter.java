package ru.geekbrains.march.market.cart.converters;

import org.springframework.stereotype.Component;

import ru.geekbrains.march.market.api.CartItemDto;
import ru.geekbrains.march.market.cart.utils.CartItem;

@Component
public class CartItemConverter {
    public CartItemDto entityToDto(CartItem cartItem) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setPrice(cartItem.getPrice());
        cartItemDto.setProductId(cartItem.getProductId());
        cartItemDto.setQuantity(cartItem.getQuantity());
        cartItemDto.setProductTitle(cartItem.getProductTitle());
        cartItemDto.setPricePerProduct(cartItem.getPricePerProduct());
        return cartItemDto;
    }
}
