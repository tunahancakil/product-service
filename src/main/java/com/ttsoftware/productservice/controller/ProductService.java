package com.ttsoftware.productservice.controller;

import com.ttsoftware.productservice.controller.mapper.ProductMapper;
import com.ttsoftware.productservice.model.dto.ProductDto;
import com.ttsoftware.productservice.model.entity.Product;
import com.ttsoftware.productservice.model.entity.ProductImage;
import com.ttsoftware.productservice.repository.ProductImageRepository;
import com.ttsoftware.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    public ProductDto getProductById(Long id) {
        Optional<Product> court  = productRepository.findById(id);
        return court.map(productMapper::toProductDto).orElse(null);
    }

    public Product createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(product.getDescription());
        product.setPrice(productDto.getPrice());
        productRepository.save(product);

        ProductImage productImage = new ProductImage();
        productDto.getImages().forEach(imageDto -> {
            productImage.setProduct(product);
            productImage.setImage(imageDto.getImage());
            productImage.setColor(imageDto.getColor());
            productImage.setId(product.getId());
            productImageRepository.save(productImage);
        });
        return product;
    }

//    public CourtDeleteResponse deleteCourt(Long id) {
//        CourtDeleteResponse response = new CourtDeleteResponse();
//        if (productRepository.existsById(id)) {
//            productRepository.deleteById(id);
//            response.setStatus(0);
//            response.setDescription("Operation is done.");
//        } else {
//            response.setStatus(-1);
//            response.setDescription("Record is not found.");
//        }
//        return response;
//    }

    public ResponseEntity<String> updateProduct(ProductDto courtDto) {
        try {
            Optional<Product> optionalCourt = productRepository.findById(courtDto.getId());
            if (optionalCourt.isPresent()) {
                Product court = optionalCourt.get();
                productRepository.save(court);
                return new ResponseEntity<>("Operation is done", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Court is not found.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity<>("Court update has an error : " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public List<ProductDto> getAllProducts() {
        return productMapper.toProductDtoList(productRepository.findAll());
    }
}
