package com.ttsoftware.productservice.application.service.payment;

import com.ttsoftware.productservice.application.mapper.PaymentMapper;
import com.ttsoftware.productservice.infrastructure.response.payment.PaymentDeleteResponse;
import com.ttsoftware.productservice.model.dto.payment.PaymentDto;
import com.ttsoftware.productservice.model.entity.*;
import com.ttsoftware.productservice.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository;

    public PaymentDto getPayment(Long id) {
        Optional<Payment> payment  = paymentRepository.findById(id);
        return payment.map(paymentMapper::toPaymentDto).orElse(null);
    }

    public PaymentDto createPayment(PaymentDto paymentDto) {
        Payment payment = new Payment();
        validatePaymentDto(paymentDto);
        paymentRepository.save(payment);
        return null;
    }

    public PaymentDeleteResponse deletePayment(Long id) {
        PaymentDeleteResponse response = new PaymentDeleteResponse();
        if (paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
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

    public List<PaymentDto> getAllPayment() {
        return paymentMapper.toPaymentDtoList(paymentRepository.findAll());
    }

    private void validatePaymentDto(PaymentDto paymentDto) {
    }
}
