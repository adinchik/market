package ru.geekbrains.march.market.core.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.march.market.api.ProductDto;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllProductsTest() throws Exception {
        mvc
                .perform(
                        get("/api/v1/products")
                                .contentType(MediaType.APPLICATION_JSON)

                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[3].title", is("Cheese")));
    }

    @Test
    public void createNewProductTest() throws Exception {
        ProductDto productDto = new ProductDto(null, "Demo", BigDecimal.valueOf(100.00),"Food");
        mvc
                .perform(
                        post("/api/v1/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(productDto))
                                .header("username", "Bob") // Здесь не особо нужно
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteProductByIdTest() throws Exception {
        mvc
                .perform(
                        delete("/api/v1/products/{id}", "1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}
