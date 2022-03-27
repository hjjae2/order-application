package com.toy.apps.order.scheduler.handler;

import com.toy.apps.order.entity.Order;
import com.toy.apps.order.entity.OrderItem;
import com.toy.apps.order.scheduler.handler.pre.OrderPreHandler;
import com.toy.apps.product.entity.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * OrderRequestHandler : 주문 요청 핸들러 <br><br>
 * 접수된 신규 주문서를 처리합니다.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class OrderHandlerManager {

    private final List<OrderPreHandler> orderPreHandlers;
    private final ApplicationEventPublisher orderEventPublisher;

    /**
     * [처리 절차] <br><br>
     *
     * 1. 전처리(+검증) 진행 <br>
     * 2. 처리 진행 <br>
     * 3. 후처리(+로깅) 진행 <br>
     */
    @Transactional
    public void process(Order order) {
        // 전처리 //
        if (!preHandle(order)) {
            order.fail();
            return;
        }

        // 처리 //
        handle(order);
        order.succeed();

        // 후처리 //
        orderEventPublisher.publishEvent(order);
    }

    /**
     * '전처리(+검증) 핸들러' 를 통해 전처리를 진행합니다. <br><br>
     *
     * e.g. <br>
     * 1. 상품{@link Product}의 개수를 체크합니다. <br>
     * 2. {@link OrderItem} 데이터를 체크합니다. <br>
     * ... <br><br>
     *
     * * 핸들러를 추가하여 검증/처리 로직을 추가합니다. <br>
     */
    public boolean preHandle(Order order) {
        boolean isOk = true;
        for (OrderPreHandler orderPreHandler : orderPreHandlers) {
            if (!orderPreHandler.supports(order)) {
                continue;
            }

            isOk = orderPreHandler.preHandle(order);
            if (!isOk) {
                return isOk;
            }
        }

        return isOk;
    }

    /**
     * 처리(+검증) 로직을 구현합니다. <br><br>
     *
     * e.g. <br>
     * 1. 현재 상품{@link Product}의 개수를 감소합니다 <br><br>
     *
     * * 처리 로직이 많아지면, 핸들러를 추가하여 검증/처리 로직을 추가합니다. <br>
     */
    public void handle(Order order) {
        List<OrderItem> orderItems = order.getOrderItems();

        for (OrderItem orderItem : orderItems) {
            Product product = orderItem.getProduct();
            product.minusQuantity(orderItem.getQuantity());
        }

        log.info("[주문 처리 성공] 주문 ID : {}", order.getIdentifier());
    }
}
