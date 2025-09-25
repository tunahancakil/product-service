package com.ttsoftware.productservice.application.controller.product;

import com.ttsoftware.productservice.application.service.product.ProductService;
import com.ttsoftware.productservice.infrastructure.request.UpdateProductRequest;
import com.ttsoftware.productservice.infrastructure.response.product.ProductDeleteResponse;
import com.ttsoftware.productservice.model.dto.product.ProductDto;
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
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/getProductById", produces = APPLICATION_JSON_VALUE)
    public ProductDto getProductByID(@RequestParam("id") Long id) {
        return productService.getProductById(id);
    }

    @PostMapping(value = "/createProduct", produces = APPLICATION_JSON_VALUE)
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @DeleteMapping(value = "/deleteProduct", produces = APPLICATION_JSON_VALUE)
    public ProductDeleteResponse deleteProduct(@RequestParam("id") Long id) {
        return productService.deleteProduct(id);
    }

    @PutMapping(value = "/updateProduct")
    public ResponseEntity<String> updateProduct(@RequestBody ProductDto productDto) {
        return productService.updateProduct(productDto);
    }

    @GetMapping(value = "/getAllProducts", produces = APPLICATION_JSON_VALUE)
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping(value = "/updateProductActive", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateProductActive(@RequestBody UpdateProductRequest request) {
        return productService.updateProductActive(request);
    }
}