package com.toy.apps.order.service;

import com.toy.apps.member.entity.Member;
import com.toy.apps.member.exception.MemberNotFoundException;
import com.toy.apps.member.repository.MemberRepository;
import com.toy.apps.order.dto.OrderDto;
import com.toy.apps.order.dto.OrderItemDto;
import com.toy.apps.order.entity.Order;
import com.toy.apps.order.entity.OrderItem;
import com.toy.apps.order.repository.OrderItemRepository;
import com.toy.apps.order.repository.OrdersRepository;
import com.toy.apps.product.entity.Product;
import com.toy.apps.product.exception.ProductNotFoundException;
import com.toy.apps.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * OrderRequestService : 주문 요청 서비스 <br><br>
 * 신규 주문 요청에 대한 접수를 처리합니다. (신규 주문 요청 건을 DB에 적재합니다.) <br>
 * 실질적인 처리는 {@link OrderHandlerManager} 에서 처리합니다.
 */
@RequiredArgsConstructor
@Service
public class OrderRequestService {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final OrdersRepository ordersRepository;
    private final OrderItemRepository orderItemRepository;

    /**
     * [주문] <br><br>
     *
     * 1. 새로운 주문서(Order, OrderItem) 생성 / 저장
     */
    @Transactional
    public OrderDto.Request.ResponseDto order(OrderDto.Request.RequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getMemberId()).orElseThrow(MemberNotFoundException::new);
        List<OrderItem> orderItems = new ArrayList<>();

        // 1. Order 저장합니다.
        Order order = ordersRepository.save(Order.newOrderBy(member));

        // 2. OrderItem 저장합니다.
        for (OrderItemDto.Create.RequestDto orderItemRequestDto : requestDto.getOrderItems()) {
            int orderQuantity = orderItemRequestDto.getQuantity();

            Product product = productRepository.findById(orderItemRequestDto.getProductId())
                                               .orElseThrow(() -> new ProductNotFoundException(orderItemRequestDto.getProductId()));

            orderItems.add(OrderItem.newOrderItemBy(order, product, orderQuantity));
        }
        orderItemRepository.saveAll(orderItems);

        return OrderDto.Request.ResponseDto.success();
    }
}
