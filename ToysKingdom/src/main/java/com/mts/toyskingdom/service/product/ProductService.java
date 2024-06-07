package com.mts.toyskingdom.service.product;

import com.mts.toyskingdom.data.entity.ProductE;
import com.mts.toyskingdom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public Page<ProductE> getAllProductsSortedByPrice(String order, int page, int size)
    {
        Pageable pageable = PageRequest.of(page, size, Sort.by("price").ascending());
        if("desc".equalsIgnoreCase(order))
        {
         pageable = PageRequest.of(page, size, Sort.by("price").descending());
        }
        return productRepository.findAll(pageable);
    }
}
