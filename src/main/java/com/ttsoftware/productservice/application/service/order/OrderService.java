package com.ttsoftware.productservice.application.service.order;

import com.ttsoftware.productservice.application.mapper.OrderMapper;
import com.ttsoftware.productservice.infrastructure.response.order.OrderDeleteResponse;
import com.ttsoftware.productservice.model.dto.order.OrderDto;
import com.ttsoftware.productservice.model.dto.order.OrderItemDto;
import com.ttsoftware.productservice.model.entity.*;
import com.ttsoftware.productservice.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderDto getOrderById(Long id) {
        Optional<Order> order  = orderRepository.findById(id);
        return order.map(orderMapper::toOrderDto).orElse(null);
    }

    public ResponseEntity<OrderDto> createOrder(OrderDto orderDto) {
        try {
            Optional<User> user = userRepository.findById(orderDto.getUserId());
            OrderDto response = new OrderDto();
            if (user.isEmpty()) {
                throw new EntityNotFoundException("User not found with id: " + orderDto.getUserId());
            }

            Order order = new Order();
            order.setUser(user.get());
            order.setOrderUUID(orderDto.getOrderUUID());
            order.setStatus(orderDto.getStatus());
            order.setTotalPrice(orderDto.getTotalPrice());
            order.setCreatedDate(LocalDateTime.now());
            order.setUpdatedDate(LocalDateTime.now());

            List<OrderItem> orderItemList = new ArrayList<>();
            for (OrderItemDto orderItemDto : orderDto.getOrderItems()) {
                if (orderItemDto.getProductId() != null) {
                    Optional<Product> product = productRepository.findById(orderItemDto.getProductId());
                    if (product.isEmpty()) {
                        throw new EntityNotFoundException("Product not found with id: " + orderItemDto.getProductId());
                    }
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrders(order);
                    orderItem.setProduct(product.get());
                    orderItem.setPrice(orderItemDto.getPrice());
                    orderItem.setQuantity(orderItemDto.getQuantity());
                    orderItemList.add(orderItem);
                }
            }
            order.setOrderItems(orderItemList);

            validateOrderDto(orderDto);
            Order savedOrder = orderRepository.save(order);
            response.setOrderUUID(savedOrder.getOrderUUID());
            response.setStatus(savedOrder.getStatus());
            response.setTotalPrice(savedOrder.getTotalPrice());
            response.setId(savedOrder.getId());
            response.setCreatedDate(savedOrder.getCreatedDate());
            response.setUpdatedDate(savedOrder.getUpdatedDate());
            response.setUserId(savedOrder.getUser().getId());
            List<OrderItemDto> orderItemDtoList = new ArrayList<>();
            savedOrder.getOrderItems().forEach(orderItem -> {
                OrderItemDto orderItemDto = new OrderItemDto();
                orderItemDto.setId(orderItem.getId());
                orderItemDto.setProductId(orderItem.getProduct().getId());
                orderItemDto.setPrice(orderItem.getPrice());
                orderItemDto.setQuantity(orderItem.getQuantity());
                orderItemDtoList.add(orderItemDto);
            });
            response.setOrderItems(orderItemDtoList);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Order creation has an error : {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
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
