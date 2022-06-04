package ru.geekbrains.march.market.core.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.api.OrderDto;
import ru.geekbrains.march.market.api.ProductDto;
import ru.geekbrains.march.market.api.StringResponse;
import ru.geekbrains.march.market.core.converters.OrderConverter;
import ru.geekbrains.march.market.core.exceptions.ResourceNotFoundException;
import ru.geekbrains.march.market.core.integrations.CartServiceIntegration;
import ru.geekbrains.march.market.core.services.OrderService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Tag(name = "Заказы", description = "Методы работы с заказами")
public class OrderController {
    private final OrderService orderService;
    private final CartServiceIntegration cartServiceIntegration;
    private final OrderConverter orderConverter;

    @Operation(
            summary = "Запрос на получение заказов",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = OrderDto.class))
                    )
            }
    )
    @GetMapping
    public List<OrderDto> getUserOrders(@RequestHeader String username) {
        return orderService.findUserOrders(username).stream().map(orderConverter::entityToDto).collect(Collectors.toList());
    }

    @Operation(
            summary = "Запрос на создание нового заказа",
            responses = {
                    @ApiResponse(
                            description = "Заказ успешно создан", responseCode = "201"
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewOrder(@RequestHeader String username, @RequestBody OrderDto orderDto) {
        CartDto cartDto = cartServiceIntegration.getCurrentCart(username);
        orderService.createNewOrder(username, cartDto, orderDto);
        cartServiceIntegration.clear(username);
    }
}
