package com.toy.apps.order.scheduler;

import com.toy.apps.order.entity.Order;
import com.toy.apps.order.repository.OrdersRepository;
import com.toy.apps.order.scheduler.handler.OrderHandlerManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * OrderScheduler : 주문서 처리 스케줄러
 * */
@Slf4j
@RequiredArgsConstructor
@Component
public class OrderScheduler {

    private final OrderHandlerManager orderHandlerManager;
    private final OrdersRepository ordersRepository;

    @Transactional
    @Scheduled(cron = "*/10 * * * * *")
    public void run() {
        Optional<Order> order = ordersRepository.findFirstByOrderStatusOrderByCreatedAt(Order.OrderStatus.REQUEST);
        if (order.isEmpty()) {
            log.info("[주문 처리 안내] 접수된 주문이 없습니다.");
            return;
        }

        orderHandlerManager.process(order.get());
    }
}
