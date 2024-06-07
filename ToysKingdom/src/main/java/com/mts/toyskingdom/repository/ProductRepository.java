package com.mts.toyskingdom.repository;


import com.mts.toyskingdom.data.entity.ProductE;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends JpaRepository<ProductE, Long> {
    Page<ProductE> findAll(Pageable pageable);
}
