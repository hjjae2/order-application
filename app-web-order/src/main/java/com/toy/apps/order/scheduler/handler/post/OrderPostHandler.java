package com.toy.apps.order.scheduler.handler.post;

import com.toy.apps.order.entity.Order;

/**
 * OrderPostHandler : 주문 요청 후처리 핸들러 <br><br>
 *
 * 접수된 주문서에 대한 후처리를 지원합니다.
 */
public interface OrderPostHandler {

    /**
     * 해당 주문서의 처리 여부를 결정합니다.
     * */
    boolean supports(Order order);

    /**
     * 해당 주문서를 처리합니다.
     */
    void postHandle(Order order);
}
