package com.ttsoftware.productservice.infrastructure.response.order;

import com.ttsoftware.productservice.infrastructure.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderDeleteResponse extends BaseResponse {
    private Integer orderId;
}
