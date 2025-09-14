package com.ttsoftware.productservice.application.controller.order;

import com.ttsoftware.productservice.application.service.order.OrderService;
import com.ttsoftware.productservice.infrastructure.response.order.OrderDeleteResponse;
import com.ttsoftware.productservice.model.dto.order.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping(value = "/getOrderById", produces = APPLICATION_JSON_VALUE)
    public OrderDto getOrderByID(@RequestParam("id") Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping(value = "/createOrder", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        return orderService.createOrder(orderDto);
    }

    @DeleteMapping(value = "/deleteOrder", produces = APPLICATION_JSON_VALUE)
    public OrderDeleteResponse deleteOrder(@RequestParam("id") Long id) {
        return orderService.deleteOrder(id);
    }

    @PutMapping(value = "/updateOrder")
    public ResponseEntity<String> updateOrder(@RequestBody OrderDto orderDto) {
        return orderService.updateOrder(orderDto);
    }

    @GetMapping(value = "/getAllOrders", produces = APPLICATION_JSON_VALUE)
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }
}