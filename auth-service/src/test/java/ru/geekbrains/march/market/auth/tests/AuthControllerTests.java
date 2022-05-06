package ru.geekbrains.march.market.auth.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.march.market.api.JwtRequest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTests {
    @Autowired
    private MockMvc mvc;

    @Test
    public void createAuthTokenTest() throws Exception {
        JwtRequest authRequest = new JwtRequest();
        authRequest.setUsername("bob");
        authRequest.setPassword("100");

        mvc.perform(
                post("/authenticate")
                .content(new ObjectMapper().writeValueAsString(authRequest))
                        .contentType(MediaType.APPLICATION_JSON)
            ).andDo(print()).andExpect(status().isOk());
    }
}
