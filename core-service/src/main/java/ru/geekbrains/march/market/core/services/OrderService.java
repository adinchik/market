package ru.geekbrains.march.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.api.CartItemDto;
import ru.geekbrains.march.market.core.entities.Order;
import ru.geekbrains.march.market.core.entities.OrderItem;
import ru.geekbrains.march.market.core.entities.User;
import ru.geekbrains.march.market.core.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;


    public void createNewOrder(User user, CartDto cartDto) {
        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(cartDto.getTotalPrice());
        orderRepository.save(order);
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList = orderItemService.addItemsToDB(cartDto.getItems(), order);
        order.setItems(orderItemList);
        //orderRepository.save(order);
    }
}
