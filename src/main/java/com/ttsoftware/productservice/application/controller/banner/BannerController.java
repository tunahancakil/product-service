package com.ttsoftware.productservice.application.controller.banner;

import com.ttsoftware.productservice.application.service.banner.BannerService;
import com.ttsoftware.productservice.application.service.order.OrderService;
import com.ttsoftware.productservice.infrastructure.request.CreateBannerRequest;
import com.ttsoftware.productservice.infrastructure.response.order.OrderDeleteResponse;
import com.ttsoftware.productservice.model.dto.order.OrderDto;
import com.ttsoftware.productservice.model.entity.Banner;
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
@RequestMapping("/api/banner")
public class BannerController {

    private final BannerService bannerService;

    @GetMapping(value = "/getBannerById", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Banner> getBannerById(@RequestParam("id") Long id) {
        return bannerService.getBannerById(id);
    }

    @PostMapping(value = "/createBanner", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createBanner(@RequestBody CreateBannerRequest request) {
        return bannerService.createBanner(request);
    }

    @DeleteMapping(value = "/deleteBanner", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteBanner(@RequestParam("id") Long id) {
        return bannerService.deleteBanner(id);
    }

    @GetMapping(value = "/getAllBanners", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Banner>> getAllOrders() {
        return bannerService.getAllBanners();
    }

}