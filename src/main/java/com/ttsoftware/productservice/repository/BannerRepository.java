package com.ttsoftware.productservice.repository;

import com.ttsoftware.productservice.model.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<Banner, Long> {
}
