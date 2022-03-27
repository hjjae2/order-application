package com.toy.apps.order.entity;

import com.toy.apps.member.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {

    @DisplayName("[newOrderBy] 신규 주문 엔티티 검증")
    @Test
    void newOrderBy() {
        // given
        Member member = Member.builder().build();

        // when
        Order newOrder = Order.newOrderBy(member);

        // then
        Assertions.assertThat(newOrder.getId()).isNotNull();
        Assertions.assertThat(newOrder.getMember()).isNotNull();
        Assertions.assertThat(newOrder.getOrderStatus()).isEqualTo(Order.OrderStatus.REQUEST);
    }

    @DisplayName("[succeed] 주문서 성공(success) 메서드 검증")
    @Test
    void succeed() {
        // given
        Member member = Member.builder().build();
        Order newOrder = Order.newOrderBy(member);

        // when
        newOrder.succeed();

        // then
        Assertions.assertThat(newOrder.getOrderStatus()).isEqualTo(Order.OrderStatus.SUCCESS);
    }

    @DisplayName("[fail] 주문서 실패(failure) 메서드 검증")
    @Test
    void fail() {
        // given
        Member member = Member.builder().build();
        Order newOrder = Order.newOrderBy(member);

        // when
        newOrder.fail();

        // then
        Assertions.assertThat(newOrder.getOrderStatus()).isEqualTo(Order.OrderStatus.FAILURE);
    }
}