package ru.geekbrains.march.market.cart.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.api.StringResponse;
import ru.geekbrains.march.market.cart.converters.CartConverter;
import ru.geekbrains.march.market.cart.services.CartService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Tag(name = "Корзина", description = "Методы работы с корзиной")
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @Operation(
            summary = "Запрос на создание ID корзины у гостя",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = StringResponse.class))
                    )
            }
    )
    @GetMapping("/generate_id")
    public StringResponse generateGuestCartId() {
        return new StringResponse(UUID.randomUUID().toString());
    }

    @Operation(
            summary = "Запрос на получение корзины",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = CartDto.class))
                    )
            }
    )
    @GetMapping("/{guestCartId}")
    public CartDto getCurrentCart(@RequestHeader(required = false) String username, @PathVariable @Parameter(description = "ID корзины гостя", required = true) String guestCartId) {
        String currentCartId = selectCartId(username, guestCartId);
        return cartConverter.entityToDto(cartService.getCurrentCart(currentCartId));
    }

    @Operation(
            summary = "Запрос на добавление продукта в корзину",
            responses = {
                    @ApiResponse(
                            description = "Успешно", responseCode = "200"
                    )
            }
    )
    @GetMapping("/{guestCartId}/add/{productId}")
    public void addProductToCart(@RequestHeader(required = false) String username, @PathVariable @Parameter(description = "ID корзины гостя", required = true) String guestCartId, @PathVariable @Parameter(description = "ID продукта", required = true) Long productId) {
        String currentCartId = selectCartId(username, guestCartId);
        cartService.addToCart(currentCartId, productId);
    }

    @Operation(
            summary = "Запрос на очищение корзины",
            responses = {
                    @ApiResponse(
                            description = "Успешно", responseCode = "200"
                    )
            }
    )
    @GetMapping("/{guestCartId}/clear")
    public void clearCurrentCart(@RequestHeader(required = false) String username, @PathVariable @Parameter(description = "ID корзины гостя", required = true) String guestCartId) {
        String currentCartId = selectCartId(username, guestCartId);
        cartService.deleteAllItems(currentCartId);
    }

    @Operation(
            summary = "Запрос на удаление продукта из корзины",
            responses = {
                    @ApiResponse(
                            description = "Успешно", responseCode = "200"
                    )
            }
    )
    @DeleteMapping("/{guestCartId}/{productId}")
    public void deleteProductFromCart(@RequestHeader(required = false) String username, @PathVariable @Parameter(description = "ID корзины гостя", required = true) String guestCartId, @PathVariable @Parameter(description = "ID продукта", required = true) Long productId) {
        String currentCartId = selectCartId(username, guestCartId);
        cartService.deleteFromCart(currentCartId, productId);
    }

    private String selectCartId(String username, String guestCartId) {
        if (username != null) {
            return username;
        }
        return guestCartId;
    }
}
