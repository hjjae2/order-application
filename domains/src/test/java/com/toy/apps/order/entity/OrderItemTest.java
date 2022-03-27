package com.toy.apps.order.entity;

import com.toy.apps.member.entity.Member;
import com.toy.apps.product.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderItemTest {

    @Test
    void newOrderItemBy() {
        // given
        Member member = Member.builder().build();
        Order order = Order.newOrderBy(member);
        Product product = Product.builder().id(1L).build();

        // when
        OrderItem orderItem = OrderItem.newOrderItemBy(order, product, 10);

        // then
        Assertions.assertThat(orderItem.getOrder().getId()).isEqualTo(order.getId());
        Assertions.assertThat(orderItem.getProduct().getId()).isEqualTo(product.getId());
        Assertions.assertThat(orderItem.getQuantity()).isEqualTo(10);
        Assertions.assertThat(orderItem.getOrder().getOrderItems().size()).isEqualTo(1);
    }
}