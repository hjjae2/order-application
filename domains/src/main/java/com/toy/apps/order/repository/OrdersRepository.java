package com.toy.apps.order.repository;

import com.toy.apps.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Order, String> {

    List<Order> findAllByMemberId(Long memberId);

    Optional<Order> findFirstByOrderStatusOrderByCreatedAt(Order.OrderStatus status);
}
