package com.ttsoftware.productservice.infrastructure.response.payment;

import com.ttsoftware.productservice.infrastructure.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PaymentDeleteResponse extends BaseResponse {
    private Integer paymentTransactionId;
}
