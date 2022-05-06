package ru.geekbrains.march.market.auth.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.march.market.auth.entities.User;
import ru.geekbrains.march.market.auth.exceptions.ResourceNotFoundException;
import ru.geekbrains.march.market.auth.services.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    private final UserService userService;

    @GetMapping("/get_my_email")
    public String getEmail() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User с username: " + username + " не найден"));
        return user.getEmail();
    }
}
