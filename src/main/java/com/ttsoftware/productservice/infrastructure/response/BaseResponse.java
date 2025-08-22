package com.ttsoftware.productservice.infrastructure.response;

import lombok.Data;

@Data
public class BaseResponse {
    private boolean success;
    private Integer errorCode;
    private String errorMessage;
}
