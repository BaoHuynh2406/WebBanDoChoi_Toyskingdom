package com.mts.toyskingdom.service.product;

import com.mts.toyskingdom.data.entity.ProductE;
import com.mts.toyskingdom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Phương thức sắp xếp sản phẩm theo giá tăng dần
    public List<ProductE> getProductsSortedByPriceAsc() {
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, "price"));
    }

    // Phương thức sắp xếp sản phẩm theo giá giảm dần
    public List<ProductE> getProductsSortedByPriceDesc() {
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "price"));
    }
}
