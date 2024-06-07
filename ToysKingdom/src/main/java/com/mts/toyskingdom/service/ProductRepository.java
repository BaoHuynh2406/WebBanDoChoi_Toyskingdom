package com.mts.toyskingdom.service;

import com.mts.toyskingdom.data.entity.ProductE;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface ProductRepository extends JpaRepository<ProductE, Long>
{
    Page<ProductE> findAll(Pageable pageable);
}
