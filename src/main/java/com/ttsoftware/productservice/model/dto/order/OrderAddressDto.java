package com.ttsoftware.productservice.model.dto.order;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderAddressDto {
    private String emailAddress;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String district;
    private String zipCode;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}