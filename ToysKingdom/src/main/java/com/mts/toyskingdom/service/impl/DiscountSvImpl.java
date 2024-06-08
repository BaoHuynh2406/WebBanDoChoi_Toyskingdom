package com.mts.toyskingdom.service.impl;

import com.mts.toyskingdom.data.model.DiscountAndMoreM;
import com.mts.toyskingdom.mapper.DiscountMapper;
import com.mts.toyskingdom.service.DiscountSv;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DiscountSvImpl implements DiscountSv {

    final DiscountMapper mapper;

    @Override
    public List<DiscountAndMoreM> getAllDiscount() throws SQLException {
        var listResultEntity = mapper.getAllDiscount();
        if(Objects.nonNull(listResultEntity)){
            return DiscountAndMoreM.convertListDiscountEtoListDiscountM(listResultEntity);
        }
        return null;
    }

    @Override
    public List<DiscountAndMoreM> getAllDiscountActive() throws SQLException {
        var listResultEntity = mapper.getAllDiscountActive();
        if(Objects.nonNull(listResultEntity)){
            return DiscountAndMoreM.convertListDiscountEtoListDiscountM(listResultEntity);
        }
        return null;
    }
}
