package com.toy.apps.product.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.apps.SecuritySetUp4ControllerTest;
import com.toy.apps.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(value = ProductApiController.class)
class ProductApiControllerTest extends SecuritySetUp4ControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ProductService productService;

    @BeforeEach
    void setUp() {
        setUpNormalUserRole();
    }

    @DisplayName("[findAll] 상품 전체 조회")
    @Test
    void findAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.result").value("true"))
               .andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
               .andDo(MockMvcResultHandlers.print())
               .andReturn().getResponse();
    }
}