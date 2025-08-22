package com.ttsoftware.productservice.application.service.order;

import com.ttsoftware.productservice.application.mapper.OrderMapper;
import com.ttsoftware.productservice.infrastructure.response.order.OrderDeleteResponse;
import com.ttsoftware.productservice.model.dto.order.OrderDto;
import com.ttsoftware.productservice.model.entity.*;
import com.ttsoftware.productservice.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    public OrderDto getOrderById(Long id) {
        Optional<Order> order  = orderRepository.findById(id);
        return order.map(orderMapper::toOrderDto).orElse(null);
    }

    public OrderDto createOrder(OrderDto orderDto) {
        Order order = new Order();
        validateOrderDto(orderDto);

        orderRepository.save(order);

        Optional<Order> savedOrder  = orderRepository.findById(order.getId());
        return savedOrder.map(orderMapper::toOrderDto).orElse(null);
    }

    public OrderDeleteResponse deleteOrder(Long id) {
        OrderDeleteResponse response = new OrderDeleteResponse();
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            response.setSuccess(Boolean.TRUE);
            response.setErrorCode(HttpStatus.NO_CONTENT.value());
            response.setErrorMessage("Operation is done.");
        } else {
            response.setSuccess(Boolean.FALSE);
            response.setErrorCode(HttpStatus.NOT_FOUND.value());
            response.setErrorMessage("Record is not found.");
        }
        return response;
    }

    public ResponseEntity<String> updateOrder(OrderDto orderDto) {
        try {
            Optional<Order> optionalOrder = orderRepository.findById(orderDto.getId());
            validateOrderDto(orderDto);

            return null;
        } catch (Exception e){
            return new ResponseEntity<>("Order update has an error : " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public List<OrderDto> getAllOrders() {
        return orderMapper.toOrderDtoList(orderRepository.findAll());
    }

    private void validateOrderDto(OrderDto orderDto) {
    }
}
