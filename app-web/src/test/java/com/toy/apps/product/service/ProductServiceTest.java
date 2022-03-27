package com.toy.apps.product.service;

import com.toy.apps.product.entity.Product;
import com.toy.apps.product.dto.ProductDto;
import com.toy.apps.product.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest(
        classes = {ProductService.class}
)
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @MockBean
    ProductRepository productRepository;

    @Test
    void findAll() {
        // given
        Product product1 = Product.builder().id(1L).name("p1").build();
        Product product2 = Product.builder().id(2L).name("p1").build();
        Mockito.when(productRepository.findAll()).thenReturn(List.of(product1, product2));

        // when
        List<ProductDto.Read.ResponseDto> all = productService.findAll();

        // then
        Assertions.assertThat(all.size()).isEqualTo(2);
        Assertions.assertThat(all.stream().anyMatch(one -> one.getId() == 1L)).isTrue();
        Assertions.assertThat(all.stream().anyMatch(one -> one.getId() == 2L)).isTrue();

    }

    @DisplayName("[findAllByName] 2건 반환")
    @Test
    void findAllByName_경우_2건반환() {
        // given
        String name = "p1";
        Product product1 = Product.builder().id(1L).name("p1").build();
        Product product2 = Product.builder().id(2L).name("p1").build();
        Mockito.when(productRepository.findAllByName(name)).thenReturn(List.of(product1, product2));

        // when
        List<ProductDto.Read.ResponseDto> all = productService.findAllByName(name);

        // then
        Assertions.assertThat(all.size()).isEqualTo(2);
        Assertions.assertThat(all.stream().anyMatch(one -> one.getId() == 1L)).isTrue();
        Assertions.assertThat(all.stream().anyMatch(one -> one.getId() == 2L)).isTrue();
        Assertions.assertThat(all.stream().allMatch(one -> name.equals(one.getName()))).isTrue();
    }

    @DisplayName("[findAllByName] 0건 반환")
    @Test
    void findAllByName_경우_0건반환() {
        // given
        String name = "p1";
        Mockito.when(productRepository.findAllByName(name)).thenReturn(List.of());

        // when
        List<ProductDto.Read.ResponseDto> all = productService.findAllByName(name);

        // then
        Assertions.assertThat(all.isEmpty()).isTrue();
    }
}