package com.mts.toyskingdom.service.impl;

import com.mts.toyskingdom.data.model.ProductFeatureM;
import com.mts.toyskingdom.data.model.ProductM;
import com.mts.toyskingdom.mapper.ProductMapper;
import com.mts.toyskingdom.service.ProductSv;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductSvImpl implements ProductSv {

    final ProductMapper productMapper;

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
    public ProductM getProductByID(int idProduct) throws SQLException {
        var listResultEntity = productMapper.getProductByID(idProduct);
        if (Objects.nonNull(listResultEntity)) {
            return ProductM.convertProductEToProductM(listResultEntity);
        }
        return null;
    }

    @Override
    public List<ProductFeatureM> getProductFeature() throws SQLException {
        var listResultEntity = productMapper.getProductFeature();
        if (Objects.nonNull(listResultEntity)) {
            return ProductFeatureM.convertListProductEToProductM(listResultEntity);
        }
        return null;
    }
}
