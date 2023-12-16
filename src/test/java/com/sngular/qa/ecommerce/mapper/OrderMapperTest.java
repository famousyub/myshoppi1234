package com.sngular.qa.ecommerce.mapper;

import com.sngular.qa.ecommerce.domain.Order;
import com.sngular.qa.ecommerce.dto.order.OrderRequest;
import com.sngular.qa.ecommerce.dto.order.OrderResponse;
import com.sngular.qa.ecommerce.util.TestConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMapperTest {

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void convertToEntity() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setFirstName(TestConstants.FIRST_NAME);
        orderRequest.setLastName(TestConstants.LAST_NAME);
        orderRequest.setCity(TestConstants.CITY);
        orderRequest.setAddress(TestConstants.ADDRESS);
        orderRequest.setEmail(TestConstants.ORDER_EMAIL);
        orderRequest.setPostIndex(TestConstants.POST_INDEX);
        orderRequest.setPhoneNumber(TestConstants.PHONE_NUMBER);
        orderRequest.setTotalPrice(TestConstants.TOTAL_PRICE);

        Order order = modelMapper.map(orderRequest, Order.class);
        assertEquals(orderRequest.getFirstName(), order.getFirstName());
        assertEquals(orderRequest.getLastName(), order.getLastName());
        assertEquals(orderRequest.getAddress(), order.getAddress());
        assertEquals(orderRequest.getCity(), order.getCity());
        assertEquals(orderRequest.getEmail(), order.getEmail());
        assertEquals(orderRequest.getPostIndex(), order.getPostIndex());
        assertEquals(orderRequest.getPhoneNumber(), order.getPhoneNumber());
        assertEquals(orderRequest.getTotalPrice(), order.getTotalPrice());
    }

    @Test
    public void convertToResponseDto() {
        Order order = new Order();
        order.setId(1L);
        order.setFirstName(TestConstants.FIRST_NAME);
        order.setLastName(TestConstants.LAST_NAME);
        order.setCity(TestConstants.CITY);
        order.setAddress(TestConstants.ADDRESS);
        order.setEmail(TestConstants.ORDER_EMAIL);
        order.setPostIndex(TestConstants.POST_INDEX);
        order.setPhoneNumber(TestConstants.PHONE_NUMBER);
        order.setTotalPrice(TestConstants.TOTAL_PRICE);

        OrderResponse orderResponse = modelMapper.map(order, OrderResponse.class);
        assertEquals(order.getId(), orderResponse.getId());
        assertEquals(order.getFirstName(), orderResponse.getFirstName());
        assertEquals(order.getLastName(), orderResponse.getLastName());
        assertEquals(order.getAddress(), orderResponse.getAddress());
        assertEquals(order.getCity(), orderResponse.getCity());
        assertEquals(order.getEmail(), orderResponse.getEmail());
        assertEquals(order.getPostIndex(), orderResponse.getPostIndex());
        assertEquals(order.getPhoneNumber(), orderResponse.getPhoneNumber());
        assertEquals(order.getTotalPrice(), orderResponse.getTotalPrice());
    }
}
