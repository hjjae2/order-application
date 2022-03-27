package com.toy.apps.order.scheduler.handler.post;

import com.toy.apps.member.entity.Member;
import com.toy.apps.order.entity.Order;
import com.toy.apps.order.entity.OrderItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;

/**
 * EmailOrderHandler <br><br>
 *
 * 주문 후처리 : Email 발송, 관계자(구매자, 판매자, 관리자 등)에게 이메일을 발송합니다.
 */
@Slf4j
@Component
public class EmailOrderPostHandler implements OrderPostHandler {

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

        // 관계자(구매자, 판매자, 관리자 등)에게 이메일을 발송합니다.
        List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            Member seller = orderItem.getProduct().getSeller();

            sendToSeller(seller.getEmail());
        }

        sendToBuyer(order.getMember().getEmail());
    }

    private void sendToBuyer(String email) {
        log.info("[주문 후처리 - Email 발송] 구매자 {}에게 이메일을 발송하였습니다.", email);
    }

    private void sendToSeller(String email) {
        log.info("[주문 후처리 - Email 발송] 판매자 {}에게 이메일을 발송하였습니다.", email);
    }

    private void sendToAdmin(String email) {
        log.info("[주문 후처리 - Email 발송] 관리자 {}에게 이메일을 발송하였습니다.", email);
    }
}
