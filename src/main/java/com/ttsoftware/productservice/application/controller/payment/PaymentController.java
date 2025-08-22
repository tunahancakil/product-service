package com.ttsoftware.productservice.application.controller.payment;

import com.ttsoftware.productservice.application.service.payment.PaymentService;
import com.ttsoftware.productservice.infrastructure.response.payment.PaymentDeleteResponse;
import com.ttsoftware.productservice.model.dto.payment.PaymentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping(value = "/getPaymentById", produces = APPLICATION_JSON_VALUE)
    public PaymentDto getPaymentByID(@RequestParam("id") Long id) {
        return paymentService.getPayment(id);
    }

    @PostMapping(value = "/createPayment", produces = APPLICATION_JSON_VALUE)
    public PaymentDto createPayment(@RequestBody PaymentDto paymentDto) {
        return paymentService.createPayment(paymentDto);
    }

    @DeleteMapping(value = "/deletePayment", produces = APPLICATION_JSON_VALUE)
    public PaymentDeleteResponse deletePayment(@RequestParam("id") Long id) {
        return paymentService.deletePayment(id);
    }

    @GetMapping(value = "/getAllPayments", produces = APPLICATION_JSON_VALUE)
    public List<PaymentDto> getAllPayments() {
        return paymentService.getAllPayment();
    }
}