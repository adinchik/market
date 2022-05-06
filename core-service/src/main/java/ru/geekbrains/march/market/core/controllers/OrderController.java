package ru.geekbrains.march.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.core.exceptions.ResourceNotFoundException;
import ru.geekbrains.march.market.core.integrations.CartServiceIntegration;
import ru.geekbrains.march.market.core.services.OrderService;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final CartServiceIntegration cartServiceIntegration;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewOrder(@RequestHeader String username) {
        //User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Юзер с именем: " + principal.getName() + " не найден"));
        CartDto cartDto = cartServiceIntegration.getCurrentCart();
        orderService.createNewOrder(username, cartDto);
    }
}
