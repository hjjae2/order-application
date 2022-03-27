package com.toy.apps.order.web;

import com.toy.apps.order.dto.OrderDto;
import com.toy.apps.order.service.OrderRequestService;
import com.toy.apps.order.service.OrderRetrieveService;
import com.toy.apps.member.service.ToyMemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/orders")
@RestController
public class OrderApiController {

    private final OrderRequestService orderRequestService;
    private final OrderRetrieveService orderRetrieveService;

    @PostMapping
    public OrderDto.Request.ResponseDto request(@AuthenticationPrincipal ToyMemberDetails toyMemberDetails,
                                                @Valid @RequestBody OrderDto.Request.RequestDto requestDto) {
        requestDto.setMemberId(toyMemberDetails.getId());
        return orderRequestService.order(requestDto);
    }

    @GetMapping
    public List<OrderDto.Read.ResponseDto> read(@AuthenticationPrincipal ToyMemberDetails toyMemberDetails) {
        return orderRetrieveService.findAllBy(toyMemberDetails.getId());
    }
}
