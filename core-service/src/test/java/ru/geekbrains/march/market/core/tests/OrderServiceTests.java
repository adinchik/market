package ru.geekbrains.march.market.core.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.march.market.api.CartDto;
import ru.geekbrains.march.market.api.CartItemDto;
import ru.geekbrains.march.market.api.OrderDto;
import ru.geekbrains.march.market.core.entities.Category;
import ru.geekbrains.march.market.core.entities.Order;
import ru.geekbrains.march.market.core.entities.OrderItem;
import ru.geekbrains.march.market.core.entities.Product;
import ru.geekbrains.march.market.core.repositories.OrderRepository;
import ru.geekbrains.march.market.core.services.OrderService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest(classes = OrderService.class)
@ActiveProfiles("test")
public class OrderServiceTests {
    @Autowired
    private OrderService orderService;
    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void createNewOrderTest() {
        List<OrderItem> orderItemList = new ArrayList<>();
        Order order = new Order();
        order.setUsername("bob");
        order.setTotalPrice(BigDecimal.valueOf(100));
        order.setId(1L);
        OrderItem orderItem = new OrderItem();
        Category category = new Category();
        category.setId(1L);
        category.setTitle("Food");
        category.setProducts(Collections.emptyList());
        Product product = new Product();
        product.setId(1L);
        product.setPrice(BigDecimal.valueOf(100));
        product.setTitle("Orange");
        product.setCategory(category);
        orderItem.setProduct(product);
        orderItem.setQuantity(1);
        orderItem.setPricePerProduct(BigDecimal.valueOf(100));
        orderItem.setPrice(BigDecimal.valueOf(100));
        orderItem.setId(1L);
        orderItem.setOrder(order);
        orderItemList.add(orderItem);
        order.setItems(orderItemList);

        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setPrice(BigDecimal.valueOf(100));
        cartItemDto.setPricePerProduct(BigDecimal.valueOf(100));
        cartItemDto.setQuantity(1);
        cartItemDto.setProductId(1L);
        cartItemDto.setProductTitle("Orange");
        List<CartItemDto> cartItemDtoList = new ArrayList<>();
        cartItemDtoList.add(cartItemDto);
        CartDto cartDto = new CartDto();
        cartDto.setTotalPrice(BigDecimal.valueOf(100));
        cartDto.setItems(cartItemDtoList);

        OrderDto orderDto = new OrderDto();
        orderDto.setAddress("lalala");
        orderDto.setPhoneNumber("777");
        orderDto.setItems(new ArrayList<>());
        orderDto.setId(1L);
        orderDto.setTotalPrice(BigDecimal.ZERO);

        orderService.createNewOrder("bob", cartDto, orderDto);

        Mockito.verify(orderRepository, Mockito.times(1)).save(ArgumentMatchers.any());

        Assertions.assertEquals(BigDecimal.valueOf(100), orderItemList.get(0).getPrice());

    }
}
