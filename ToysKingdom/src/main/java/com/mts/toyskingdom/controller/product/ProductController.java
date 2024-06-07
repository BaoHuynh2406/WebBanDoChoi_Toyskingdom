package com.mts.toyskingdom.controller.product;

import com.mts.toyskingdom.api.ProductAPI;
import com.mts.toyskingdom.data.entity.ProductE;
import com.mts.toyskingdom.service.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/sorted")
    public ResponseEntity<Page<ProductE>> getProductsSortedByPrice(
            @RequestParam(defaultValue = "asc") String order,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<ProductE> products = productService.getAllProductsSortedByPrice(order, page, size);
        return ResponseEntity.ok(products);
    }
}
