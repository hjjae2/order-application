package com.toy.apps.product.entity;

import com.toy.apps.product.exception.ProductValidationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductTest {

    @DisplayName("[isQuantityOverThan] 예상 결과 : true (상품 수량 : 2 > 비교 수랑 : 1)")
    @Test
    void isQuantityOverThan_경우_상품수량2_비교수량1() {
        // given
        Product product = Product.builder().quantity(2).build();

        // when
        boolean isQuantityOverThan = product.isQuantityOverThan(1);

        // then
        Assertions.assertThat(isQuantityOverThan).isTrue();
    }

    @DisplayName("[isQuantityOverThan] 예상 결과 : true (상품 수량 : 2 > 비교 수랑 : 2)")
    @Test
    void isQuantityOverThan_경우_상품수량2_비교수량2() {
        // given
        Product product = Product.builder().quantity(2).build();

        // when
        boolean isQuantityOverThan = product.isQuantityOverThan(2);

        // then
        Assertions.assertThat(isQuantityOverThan).isTrue();
    }

    @DisplayName("[isQuantityOverThan] 예상 결과 : false (상품 수량 : 2 > 비교 수랑 : 3)")
    @Test
    void isQuantityOverThan_경우_상품수량2_비교수량3() {
        // given
        Product product = Product.builder().quantity(2).build();

        // when
        boolean isQuantityOverThan = product.isQuantityOverThan(3);

        // then
        Assertions.assertThat(isQuantityOverThan).isFalse();
    }

    @DisplayName("[isQuantityOverThan] 예상 결과 : 1 (상품 수량 : 2 - 빼기 수랑 : 1)")
    @Test
    void minusQuantity_경우_상품수량2_빼기수량1() {
        // given
        Product product = Product.builder().quantity(2).build();

        // when
        product.minusQuantity(1);

        // then
        Assertions.assertThat(product.getQuantity()).isEqualTo(1);
    }

    @DisplayName("[isQuantityOverThan] 예상 결과 : 0 (상품 수량 : 2 - 빼기 수랑 : 2)")
    @Test
    void minusQuantity_경우_상품수량2_빼기수량2() {
        // given
        Product product = Product.builder().quantity(2).build();

        // when
        product.minusQuantity(2);

        // then
        Assertions.assertThat(product.getQuantity()).isEqualTo(0);
    }

    @DisplayName("[isQuantityOverThan] 예상 결과 : ProductValidationException (상품 수량 : 2 - 빼기 수랑 : 3)")
    @Test
    void minusQuantity_경우_상품수량2_빼기수량3() {
        // given
        Product product = Product.builder().quantity(2).build();

        // when // then
        Assertions.assertThatThrownBy(() -> product.minusQuantity(3)).isInstanceOf(ProductValidationException.class);
    }
}