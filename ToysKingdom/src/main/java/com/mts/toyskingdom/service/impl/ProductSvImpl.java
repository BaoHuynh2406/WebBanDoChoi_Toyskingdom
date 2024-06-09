package com.mts.toyskingdom.service.impl;


import com.mts.toyskingdom.data.dto.ProductDTO;

import com.mts.toyskingdom.data.entity.ProductE;
import com.mts.toyskingdom.data.model.ProductFeatureM;
import com.mts.toyskingdom.data.model.ProductM;
import com.mts.toyskingdom.mapper.ProductMapper;
import com.mts.toyskingdom.repository.ProductRepository;
import com.mts.toyskingdom.service.ProductSv;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductSvImpl implements ProductSv {

    @Autowired
    private ProductRepository productRepository;
    final ProductMapper productMapper;


    @Override
    public List<ProductM> getAllProductsSorted(String direction) throws SQLException {
        List<ProductE> products;
        if ("asc".equalsIgnoreCase(direction)) {
            products = productRepository.findAllByOrderByPriceAsc();
        } else {
            products = productRepository.findAllByOrderByPriceDesc();
        }
        return ProductM.convertListProductEToProductM(products);
    }

    @Override
    public List<ProductM> getAllProducts() throws SQLException {
        var listResultEntity = productMapper.getAllProducts();
        if (Objects.nonNull(listResultEntity)) {
            return ProductM.convertListProductEToProductM(listResultEntity);
        }
        return null;
    }

    @Override
    public List<ProductM> getAllProductsActive() throws SQLException {
        var listResultEntity = productMapper.getAllActiveProducts();
        if (Objects.nonNull(listResultEntity)) {
            return ProductM.convertListProductEToProductM(listResultEntity);
        }
        return null;
    }

    @Override
    public ProductFeatureM getProductByID(int idProduct) throws SQLException {
        var listResultEntity = productMapper.getProductByID(idProduct);
        if (Objects.nonNull(listResultEntity)) {
            return ProductFeatureM.convertProductEToProductFeatureM(listResultEntity);
        }
        return null;
    }


    @Override
    public List<ProductFeatureM> getAllProductsFeature() throws SQLException {
        var listResultEntity = productMapper.getAllProductsFeature();
        if (Objects.nonNull(listResultEntity)) {
            return ProductFeatureM.convertListProductEToProductFeatureM(listResultEntity);
        }
        return null;
    }

    @Override
    public List<ProductFeatureM> searchProduct(String productName) throws SQLException {

        var listResultEntity = productMapper.searchProduct(productName);
        if (Objects.nonNull(listResultEntity) && !listResultEntity.isEmpty()) {
            return ProductFeatureM.convertListProductEToProductFeatureM(listResultEntity);
        } else {
            System.out.println("Không có sản phẩm có tên là: " + productName);
            return Collections.emptyList();
        }
    }

    @Override
    public int countFeatureProducts() throws SQLException {
        int num = productMapper.countFeatureProducts();
        return num;
    }

    @Override
    public List<ProductFeatureM> getProductFeaturePage(int start, int quantity) throws SQLException {
        var listResultEntity = productMapper.getProductFeaturePage(start, quantity);
        if (Objects.nonNull(listResultEntity)) {
            return ProductFeatureM.convertListProductEToProductFeatureM(listResultEntity);
        }
        return null;
    }

    @Override
    public int saveProduct(ProductDTO productDTO) throws SQLException {
        if(productMapper.getProductByID(productDTO.getIdProduct()) == null) {
            productDTO.setActive(true);
            productMapper.insertProduct(productDTO);
            return 1;
        } else {
            productDTO.setActive(true);
            productMapper.updateProduct(productDTO);
            return 2;
        }
    }

    @Override
    public boolean deleteProduct(int idProduct) throws SQLException {
        if(productMapper.deleteProduct(idProduct) > 0) return true;
        return false;
    }

    @Override
    public boolean disableProduct(int idProduct) throws SQLException {
        if(productMapper.disableProduct(idProduct) > 0) return true;
        return false;
    }
}
