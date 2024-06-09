package com.mts.toyskingdom.controller.product;

import com.mts.toyskingdom.data.entity.ProductE;

import com.mts.toyskingdom.repository.ProductRepository;
import com.mts.toyskingdom.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/sort")
    public List<ProductE> sort(@RequestParam("direction")Optional<String> direction){
        if (direction.orElse("asc").equalsIgnoreCase("asc")) {
            return productService.getProductsSortedByPriceAsc();
        } else {
            return productService.getProductsSortedByPriceDesc();
        }
    }
}

