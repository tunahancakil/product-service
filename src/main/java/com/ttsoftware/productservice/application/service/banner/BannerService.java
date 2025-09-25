package com.ttsoftware.productservice.application.service.banner;

import com.ttsoftware.productservice.application.mapper.BannerMapper;
import com.ttsoftware.productservice.infrastructure.request.CreateBannerRequest;
import com.ttsoftware.productservice.model.entity.Banner;
import com.ttsoftware.productservice.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BannerService {

    private final BannerMapper bannerMapper;
    private final BannerRepository bannerRepository;

    public ResponseEntity<Banner> getBannerById(Long id) {
        Optional<Banner> bannerOptional = bannerRepository.findById(id);
        return bannerOptional.map(banner -> new ResponseEntity<>(banner, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<List<Banner>> getAllBanners() {
        return new ResponseEntity<>(bannerRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<String> createBanner(CreateBannerRequest request) {
        Banner banner = bannerMapper.toBanner(request);
        bannerRepository.save(banner);
        return new ResponseEntity<>("Banner created", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteBanner(Long id) {
        bannerRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
