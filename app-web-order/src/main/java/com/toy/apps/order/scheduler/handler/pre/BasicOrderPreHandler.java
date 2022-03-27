package com.toy.apps.order.scheduler.handler.pre;

import com.toy.apps.order.entity.Order;
import com.toy.apps.order.entity.OrderItem;
import com.toy.apps.product.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * BasicOrderPreHandler : 기본적인 주문 요청 전처리 핸들러 <br><br>
 *
 * 접수된 주문서에 대한 전처리를 지원합니다.
 */
@Slf4j
@Component
public class BasicOrderPreHandler implements OrderPreHandler {

    /**
     * 해당 주문서의 처리 여부를 결정합니다.
     */
    public boolean supports(Order order) {
        return true;
    }

    /**
     * 해당 주문서에 대한 전처리(검증)을 진행합니다. <br><br>
     *
     * 1. OrderItem 는 최소 1개 이상입니다. <br>
     * 2. Product 의 개수를 확인합니다. (상품 수량 > 주문 요청 수량) <br>
     */
    public boolean preHandle(Order order) {
        if (!supports(order)) {
            return true;
        }

        boolean isOk = true;

        List<OrderItem> orderItems = order.getOrderItems();

        if (orderItems.size() == 0) {
            log.info("[주문 전처리] 접수오류 :: 주문 ID : {} (정상적으로 접수된 상품/수량 내용이 없습니다.)", order.getIdentifier());
            isOk = false;
        }

        for (OrderItem orderItem : orderItems) {
            Product product = orderItem.getProduct();
            if (product.isQuantityOverThan(orderItem.getQuantity())) {
                continue;
            }

            log.info("[주문 전처리] 수량부족 :: 주문 ID : {}, 상품 ID : {}, 현재 상품 개수 : {}, 요청 상품 개수 : {})",
                     order.getIdentifier(), product.getId(), product.getQuantity(), orderItem.getQuantity());
            isOk = false;
        }

        return isOk;
    }
}
