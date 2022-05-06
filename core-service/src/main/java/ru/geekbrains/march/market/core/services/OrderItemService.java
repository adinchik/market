package ru.geekbrains.march.market.core.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.api.CartItemDto;
import ru.geekbrains.march.market.core.entities.Order;
import ru.geekbrains.march.market.core.entities.OrderItem;
import ru.geekbrains.march.market.core.exceptions.ResourceNotFoundException;
import ru.geekbrains.march.market.core.repositories.OrderItemRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;

    public List<OrderItem> addItemsToDB(List<CartItemDto> items, Order order) {
        List<OrderItem> orderItems = new ArrayList<>();
        for(CartItemDto cartItemDto: items) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setPrice(cartItemDto.getPrice());
            orderItem.setPricePerProduct(cartItemDto.getPricePerProduct());
            orderItem.setQuantity(cartItemDto.getQuantity());
            orderItem.setProduct(productService.findById(cartItemDto.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Продукт с id: " + cartItemDto.getProductId() + " не найден")));
            orderItemRepository.save(orderItem);
            orderItems.add(orderItem);
        }
        return orderItems;
    }
}
