package ru.geekbrains.march.market.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.dtos.CartDto;
import ru.geekbrains.march.market.dtos.CartItemDto;
import ru.geekbrains.march.market.utils.Cart;
import ru.geekbrains.march.market.utils.CartItem;

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