package com.ttsoftware.productservice.application.client;

import com.ttsoftware.productservice.model.dto.email.EmailDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.PostMapping;

@Import(FeignClientsConfiguration.class)
@FeignClient(value = "${feign.communication.name}", url = "${feign.communication.url}")
public interface CommunicationClient {
    @PostMapping("/email/sendMail")
    String sendMail(EmailDto emailDto);
}