package com.mts.ToysKingdom.service.impl;

import com.mts.ToysKingdom.data.model.ProductM;
import com.mts.ToysKingdom.mapper.Product_Mapper;
import com.mts.ToysKingdom.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    final Product_Mapper productMapper;

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
}
