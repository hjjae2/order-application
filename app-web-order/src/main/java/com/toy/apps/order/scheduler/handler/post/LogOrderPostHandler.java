package com.toy.apps.order.scheduler.handler.post;

import com.toy.apps.order.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * LogOrderHandler : 주문 요청 핸들러 하위 클래스 <br><br>
 *
 * 주문 후처리 : Logging
 */
@Slf4j
@Component
public class LogOrderPostHandler implements OrderPostHandler {

    @Override
    public boolean supports(Order order) {
        // 이후 order 의 조건/종류에 따라 supports 여부를 결정합니다. //
        return true;
    }

    @TransactionalEventListener
    public void postHandle(Order order) {
        if (!supports(order)) {
            return;
        }

        log.info("[주문 후처리 - 로그] 주문 ID : {} , 주문 상태 : {}, 주문자 : {}", order.getIdentifier(), order.getOrderStatus(), order.getMember().getEmail());
    }
}
