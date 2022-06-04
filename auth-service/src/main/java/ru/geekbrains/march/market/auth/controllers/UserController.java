package ru.geekbrains.march.market.auth.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.march.market.api.JwtResponse;
import ru.geekbrains.march.market.api.StringResponse;
import ru.geekbrains.march.market.auth.entities.User;
import ru.geekbrains.march.market.auth.exceptions.AppError;
import ru.geekbrains.march.market.auth.exceptions.ResourceNotFoundException;
import ru.geekbrains.march.market.auth.services.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin("*")
@Tag(name = "Пользователи", description = "Методы работы с пользователями")
public class UserController {
    private final UserService userService;

    @Operation(
            summary = "Запрос на получение email пользователя",
            responses = {
                    @ApiResponse(
                            description = "Успешный отвеn - email пользователя найден", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = StringResponse.class))
                    ),
                    @ApiResponse(
                            description = "Пользователь не найден", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = ResourceNotFoundException.class))
                    )
            }
    )
    @GetMapping("/get_my_email")
    public StringResponse getEmail() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User с username: " + username + " не найден"));
        return new StringResponse(user.getEmail());
    }
}
