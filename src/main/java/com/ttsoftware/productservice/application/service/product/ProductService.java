package com.ttsoftware.productservice.application.service.product;

import com.ttsoftware.productservice.application.mapper.ProductMapper;
import com.ttsoftware.productservice.infrastructure.response.product.ProductDeleteResponse;
import com.ttsoftware.productservice.model.dto.product.ProductDto;
import com.ttsoftware.productservice.model.dto.product.ProductImageDto;
import com.ttsoftware.productservice.model.entity.Category;
import com.ttsoftware.productservice.model.entity.Product;
import com.ttsoftware.productservice.model.entity.ProductImage;
import com.ttsoftware.productservice.model.entity.SubCategory;
import com.ttsoftware.productservice.repository.CategoryRepository;
import com.ttsoftware.productservice.repository.ProductImageRepository;
import com.ttsoftware.productservice.repository.ProductRepository;
import com.ttsoftware.productservice.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ProductImageRepository productImageRepository;

    public ProductDto getProductById(Long id) {
        Optional<Product> product  = productRepository.findById(id);
        return product.map(productMapper::toProductDto).orElse(null);
    }

    public ProductDto createProduct(ProductDto productDto) {
        Product product = new Product();
        validateProductDto(productDto);
        if (productDto.getCategory() != null) {
            Category category = categoryRepository.findByName(productDto.getCategory());
            if (category == null) {
                log.error("Category not found");
                throw new IllegalArgumentException("Category not found: " + productDto.getCategory());
            } else {
                product.setCategory(category);
            }
        }
        if (productDto.getSubCategory() != null) {
            SubCategory subCategory = subCategoryRepository.findByName(productDto.getSubCategory());
            if (subCategory == null) {
                log.error("SubCategory not found");
                throw new IllegalArgumentException("SubCategory not found: " + productDto.getSubCategory());
            } else {
                product.setSubCategory(subCategory);
            }
        }
        product.setQuantity(productDto.getQuantity());
        product.setActive(productDto.getActive());
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
        Optional<Product> savedProduct  = productRepository.findById(product.getId());
        return savedProduct.map(productMapper::toProductDto).orElse(null);
    }

    public ProductDeleteResponse deleteProduct(Long id) {
        ProductDeleteResponse response = new ProductDeleteResponse();
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            response.setSuccess(Boolean.TRUE);
            response.setErrorCode(HttpStatus.NO_CONTENT.value());
            response.setErrorMessage("Operation is done.");
        } else {
            response.setSuccess(Boolean.FALSE);
            response.setErrorCode(HttpStatus.NOT_FOUND.value());
            response.setErrorMessage("Record is not found.");
        }
        return response;
    }

    public ResponseEntity<String> updateProduct(ProductDto request) {
        try {
            Optional<Product> optionalProduct = productRepository.findById(request.getId());
            validateProductDto(request);
            Category category = categoryRepository.findByName(request.getCategory());
            if (category == null) {
                throw new IllegalArgumentException("Category not found: " + request.getCategory());
            }
            SubCategory subCategory = subCategoryRepository.findByName(request.getSubCategory());
            if (subCategory == null) {
                throw new IllegalArgumentException("SubCategory not found: " + request.getSubCategory());
            }
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                product.setActive(request.getActive());
                product.setName(request.getName());
                product.setCategory(category);
                product.setSubCategory(subCategory);
                product.setQuantity(request.getQuantity());
                product.setPrice(request.getPrice());

                List<ProductImageDto> requestImages = request.getImages();
                List<ProductImage> existingImages = productImageRepository.findByProduct(product);

                Map<Long, ProductImage> existingImageMap = existingImages.stream()
                        .filter(img -> img.getId() != null)
                        .collect(Collectors.toMap(ProductImage::getId, img -> img));

                // Gelen request'te olmayan eski image'ları sil
                for (ProductImage existing : existingImages) {
                    boolean stillExists = requestImages != null && requestImages.stream()
                            .anyMatch(reqImg -> reqImg.getId() != null && reqImg.getId().equals(existing.getId()));
                    if (!stillExists) {
                        productImageRepository.delete(existing);
                    }
                }

                // Gelenleri ekle/güncelle
                if (requestImages != null) {
                    for (ProductImageDto reqImg : requestImages) {
                        if (reqImg.getId() != null && existingImageMap.containsKey(reqImg.getId())) {
                            // Güncelle
                            ProductImage existing = existingImageMap.get(reqImg.getId());
                            existing.setImage(reqImg.getImage());
                            // Diğer alanlar varsa onları da güncelleyin
                            productImageRepository.save(existing);
                        } else {
                            // Yeni ekle
                            ProductImage productImage = new ProductImage();
                            productImage.setColor(reqImg.getColor());
                            productImage.setImage(reqImg.getImage());
                            productImage.setProduct(product);
                            productImageRepository.save(productImage);
                        }
                    }
                }

                productRepository.save(product);
                return new ResponseEntity<>("Operation is done", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Product is not found.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity<>("Product update has an error : " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public List<ProductDto> getAllProducts() {
        return productMapper.toProductDtoList(productRepository.findAll());
    }

    private void validateProductDto(ProductDto productDto) {
        if (productDto.getName() == null || productDto.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (productDto.getPrice() <= 0) {
            throw new IllegalArgumentException("Product price must be greater than zero");
        }
        if (productDto.getCategory() == null) {
            throw new IllegalArgumentException("Product category cannot be null or empty");
        }
        if (productDto.getSubCategory() == null) {
            throw new IllegalArgumentException("Product sub-category cannot be null or empty");
        }
        if (productDto.getQuantity() <= 0) {
            throw new IllegalArgumentException("Product quantity must be greater than zero");
        }
    }
}
