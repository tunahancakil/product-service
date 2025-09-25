package com.ttsoftware.productservice.infrastructure.request;

import com.ttsoftware.productservice.model.enums.BannerLocationEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateBannerRequest {
    private String title;
    private String description;
    private String imageUrl;
    private String linkUrl;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BannerLocationEnum location;
    private Boolean isActive;
}