package com.sngular.qa.ecommerce.mapper;

import com.sngular.qa.ecommerce.domain.Order;
import com.sngular.qa.ecommerce.dto.order.OrderRequest;
import com.sngular.qa.ecommerce.dto.order.OrderResponse;
import com.sngular.qa.ecommerce.exception.InputFieldException;
import com.sngular.qa.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final CommonMapper commonMapper;
    private final OrderService orderService;

    public List<OrderResponse> findAllOrders() {
        return commonMapper.convertToResponseList(orderService.findAll(), OrderResponse.class);
    }

    public List<OrderResponse> findOrderByEmail(String email) {
        return commonMapper.convertToResponseList(orderService.findOrderByEmail(email), OrderResponse.class);
    }

    public List<OrderResponse> deleteOrder(Long orderId) {
        return commonMapper.convertToResponseList(orderService.deleteOrder(orderId), OrderResponse.class);
    }

    public OrderResponse postOrder(OrderRequest orderRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        }
        Order order = orderService.postOrder(commonMapper.convertToEntity(orderRequest, Order.class), orderRequest.getPerfumesId());
        return commonMapper.convertToResponse(order, OrderResponse.class);
    }
}
