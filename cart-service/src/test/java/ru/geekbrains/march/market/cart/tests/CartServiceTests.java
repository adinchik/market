package ru.geekbrains.march.market.cart.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.march.market.api.ProductDto;
import ru.geekbrains.march.market.cart.integrations.ProductServiceIntegration;
import ru.geekbrains.march.market.cart.services.CartService;

import java.math.BigDecimal;

@SpringBootTest(classes = CartService.class)
public class CartServiceTests {
    @Autowired
    private CartService cartService;

    @MockBean
    private ProductServiceIntegration productServiceIntegration;


    @Test
    public void addToCartTest() {
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setTitle("Orange");
        productDto.setPrice(BigDecimal.valueOf(100));
        productDto.setCategoryTitle("Food");
        Mockito.doReturn(productDto)
                .when(productServiceIntegration)
                .findById(productDto.getId());
        cartService.addToCart(productDto.getId());
        Assertions.assertEquals(1, cartService.getCurrentCart().getItems().size());
    }
}
