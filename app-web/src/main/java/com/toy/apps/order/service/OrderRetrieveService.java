package com.toy.apps.order.service;

import com.toy.apps.order.entity.Order;
import com.toy.apps.order.dto.OrderDto;
import com.toy.apps.order.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * OrderRequestService : 주문 조회 서비스 <br>
 */
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderRetrieveService {

    private final OrdersRepository ordersRepository;

    public List<OrderDto.Read.ResponseDto> findAllBy(final Long memberId) {
        List<Order> order = ordersRepository.findAllByMemberId(memberId);

        return order.stream().map(OrderDto.Read.ResponseDto::of).collect(Collectors.toList());
    }
}
