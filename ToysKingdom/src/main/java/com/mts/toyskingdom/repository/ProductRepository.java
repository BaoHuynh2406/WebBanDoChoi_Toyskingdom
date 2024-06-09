package com.mts.toyskingdom.repository;


import com.mts.toyskingdom.data.entity.ProductE;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<ProductE, Long> {
    List<ProductE> findAll(Sort sort);
}