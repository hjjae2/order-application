package com.toy.apps.order.repository;

import com.toy.apps.member.entity.Member;
import com.toy.apps.order.entity.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    TestEntityManager em;

    @Autowired
    OrdersRepository ordersRepository;

    @DisplayName("[findAllByMemberId] Member 조회 => 0건 반환")
    @Test
    void findAllByMemberId_경우_0건조회() {
        // given
        Member member = em.persist(Member.builder().email("email").build());
        em.flush();
        em.clear();

        // when
        List<Order> allByMember = ordersRepository.findAllByMemberId(member.getId());

        // then
        Assertions.assertThat(allByMember.size()).isEqualTo(0);
    }

    @DisplayName("[findAllByMemberId] Member 조회 => 2건 반환")
    @Test
    void findAllByMemberId_경우_2건조회() {
        // given
        Member member = em.persist(Member.builder().email("email").build());
        em.persist(Order.builder().id("id1").member(member).orderStatus(Order.OrderStatus.REQUEST).build());
        em.persist(Order.builder().id("id2").member(member).orderStatus(Order.OrderStatus.FAILURE).build());
        em.flush();
        em.clear();

        // when
        List<Order> allByMember = ordersRepository.findAllByMemberId(member.getId());

        // then
        Assertions.assertThat(allByMember.size()).isEqualTo(2);
    }

    @DisplayName("[findFirstByOrderStatusOrderByCreatedAt] OrderStatus 기반 조회 + created_at 정렬 => 1건 반환")
    @Test
    void findFirstByOrderStatusOrderByCreatedAt_경우_1() {
        /*
         * given : REQUEST 2건(created_at : 오늘, 내일), SUCCESS 1건(created_at : 어제)
         * then  : REQUEST 1건(created_at : 오늘)
         * */
        // given
        Member member = em.persist(Member.builder().email("email").build());
        em.persist(Order.builder().id("0").member(member).orderStatus(Order.OrderStatus.SUCCESS).createdAt(LocalDateTime.now().minusDays(1)).build());
        em.persist(Order.builder().id("1").member(member).orderStatus(Order.OrderStatus.REQUEST).createdAt(LocalDateTime.now()).build());
        em.persist(Order.builder().id("2").member(member).orderStatus(Order.OrderStatus.REQUEST).createdAt(LocalDateTime.now().plusDays(1)).build());
        em.flush();
        em.clear();

        // when
        Order order = ordersRepository.findFirstByOrderStatusOrderByCreatedAt(Order.OrderStatus.REQUEST).orElse(null);

        // then
        Assertions.assertThat(order).isNotNull();
        Assertions.assertThat(order.getOrderStatus()).isEqualTo(Order.OrderStatus.REQUEST);
        Assertions.assertThat(order.getId()).isEqualTo("1");
    }

    @DisplayName("[findFirstByOrderStatusOrderByCreatedAt] OrderStatus 기반 조회 + created_at 정렬 => 0건 반환")
    @Test
    void findFirstByOrderStatusOrderByCreatedAt_경우_2() {
        /*
         * given : SUCCESS 1건(created_at : 어제), FAILURE 1건(created_at : 오늘)
         * then  : 없음
         * */
        // given
        Member member = em.persist(Member.builder().email("email").build());
        em.persist(Order.builder().id("0").member(member).orderStatus(Order.OrderStatus.SUCCESS).createdAt(LocalDateTime.now().minusDays(1)).build());
        em.persist(Order.builder().id("1").member(member).orderStatus(Order.OrderStatus.FAILURE).createdAt(LocalDateTime.now()).build());
        em.flush();
        em.clear();

        // when
        Order order = ordersRepository.findFirstByOrderStatusOrderByCreatedAt(Order.OrderStatus.REQUEST).orElse(null);

        // then
        Assertions.assertThat(order).isNull();
    }
}