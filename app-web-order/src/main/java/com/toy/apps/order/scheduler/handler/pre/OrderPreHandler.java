package com.toy.apps.order.scheduler.handler.pre;

import com.toy.apps.order.entity.Order;

/**
 * OrderPreHandler : 주문 요청 전처리 핸들러 <br><br>
 *
 * 접수된 주문서에 대한 전처리를 지원합니다.
 */
public interface OrderPreHandler {

    /**
     * 해당 주문서의 처리 여부를 결정합니다.
     * */
    boolean supports(Order order);

    /**
     * 해당 주문서를 처리합니다.
     */
    boolean preHandle(Order order);
}
