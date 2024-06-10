package com.mts.toyskingdom.service.impl;

import com.mts.toyskingdom.data.dto.DiscountDTO;
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
        if (Objects.nonNull(listResultEntity)) {
            return DiscountAndMoreM.convertListDiscountEtoListDiscountM(listResultEntity);
        }
        return null;
    }

    @Override
    public List<DiscountAndMoreM> getAllDiscountActive() throws SQLException {
        var listResultEntity = mapper.getAllDiscountActive();
        if (Objects.nonNull(listResultEntity)) {
            return DiscountAndMoreM.convertListDiscountEtoListDiscountM(listResultEntity);
        }
        return null;
    }

    @Override
    public int save(DiscountDTO discountDTO) throws SQLException {
        if (mapper.findById(discountDTO.getIdDiscount()) == null) {
            discountDTO.setActive(true);
            System.out.println(mapper.insert(discountDTO));
            return 1;
        } else {
            discountDTO.setActive(true);
            System.out.println(mapper.update(discountDTO));
            return 2;
        }
    }

    @Override
    public boolean disable(int idDiscount) throws SQLException {
        try {
            mapper.disable(idDiscount);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(int idDiscount) throws SQLException {
        try {
            mapper.delete(idDiscount);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
