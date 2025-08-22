package com.ttsoftware.productservice.infrastructure.response.product;

import com.ttsoftware.productservice.infrastructure.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductDeleteResponse extends BaseResponse {
    private Integer productCount;
}
