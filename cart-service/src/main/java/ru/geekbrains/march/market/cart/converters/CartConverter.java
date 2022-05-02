package ru.geekbrains.march.market.cart.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.api.CartItemDto;
import ru.geekbrains.march.market.cart.utils.Cart;
import ru.geekbrains.march.market.cart.utils.CartItem;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CartConverter {
    private final CartItemConverter cartItemConverter;

    public CartDto entityToDto(Cart cart) {
        CartDto cartDto = new CartDto();
        List<CartItemDto> cartItemDtoList = new ArrayList<>();
        for (CartItem cartItem: cart.getItems()) {
            cartItemDtoList.add(cartItemConverter.entityToDto(cartItem));
        }
        cartDto.setItems(cartItemDtoList);
        cartDto.setTotalPrice(cart.getTotalPrice());
        return cartDto;
    }
}