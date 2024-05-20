//package com.mts.ToysKingdom.service.impl;
//
//import com.church.gxtanhung.systemconfigsecurity.security.UserPrincipal;
//import com.church.gxtanhung.tanhung.constant.ChangePasswordMessage;
//import com.church.gxtanhung.tanhung.constant.CommonFunction;
//import com.church.gxtanhung.tanhung.data.dto.TaiKhoanDto;
//import com.church.gxtanhung.tanhung.data.model.TaiKhoanM;
//import com.church.gxtanhung.tanhung.mapper.TaiKhoanMapper;
//import com.church.gxtanhung.tanhung.service.TaiKhoanService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//
//@Service
//@RequiredArgsConstructor
//public class TaiKhoanServiceImpl implements TaiKhoanService {
//
//    final TaiKhoanMapper taiKhoanMapper;
//
//    @Override
//    public List<TaiKhoanM> getAllTaiKhoan() throws SQLException {
//        var listResultEntity = taiKhoanMapper.getAllTaiKhoan();
//        if (Objects.nonNull(listResultEntity)) {
//            return TaiKhoanM.convertListTaiKhoanEToListTaiKhoanM(listResultEntity);
//        }
//        return null;
//    }
//
//    @Override
//    public TaiKhoanM getTaiKhoanByUserId(Long userId) throws SQLException {
//        var currentUser = taiKhoanMapper.getTaiKhoanByUserId(userId);
//        return (Objects.nonNull(currentUser)
//                ? TaiKhoanM.convertTaiKhoanEToTaiKhoanM(currentUser)
//                : null);
//    }
//
//    @Override
//    public Boolean isTaiKhoanExisted(TaiKhoanDto taiKhoanDto) throws SQLException {
//        return taiKhoanMapper.isTaiKhoanExisted(taiKhoanDto);
//    }
//
//    @Override
//    public byte updateTaiKhoan(TaiKhoanDto taiKhoanDto) throws SQLException, Exception {
//        byte rowEffected = 0;
//        var isTaiKhoanExisted = this.isTaiKhoanExisted(taiKhoanDto);
//        if (isTaiKhoanExisted) {
//            //Update taikhoan
//            rowEffected = (byte) taiKhoanMapper.updateTaiKhoan(taiKhoanDto);
//        }
//        return rowEffected;
//    }
//
//    @Override
//    public Map<String, Object> changePasswordByUserId(TaiKhoanDto taiKhoanDto, UserPrincipal userPrincipal) throws SQLException {
//        var result = new HashMap<String, Object>();
//        taiKhoanDto.setUserId(userPrincipal.getUserId());
//        var changePasswordCredentialStatus = this.validateChangePasswordCredentials(taiKhoanDto);
//        System.out.println(">>userId: " + userPrincipal.getUserId());
//        result.put("status", changePasswordCredentialStatus);
//        if (changePasswordCredentialStatus
//                .equals(ChangePasswordMessage.VALID_CREDENTIALS.getMessage())) {
//            taiKhoanDto.setMatKhauMoi(CommonFunction.encoding(taiKhoanDto.getMatKhauMoi()));
//            var rowsEffected = taiKhoanMapper.changePasswordByUserId(taiKhoanDto);
//            result.put("rowsEffected", rowsEffected);
//
//            if (rowsEffected > 0) {
//                result.put("status", ChangePasswordMessage.SUCCEED.getMessage());
//            } else {
//                result.put("status", ChangePasswordMessage.FAILED.getMessage());
//            }
//            return result;
//        }
//        return result;
//    }
//
//    private String validateChangePasswordCredentials(TaiKhoanDto taiKhoanDto) throws SQLException {
//        // check if the matKhauCu is matched with the password from DB
//        var currentUser = this.getTaiKhoanByUserId(taiKhoanDto.getUserId());
//        boolean isMatched = CommonFunction.isMatched(taiKhoanDto.getMatKhauCu(), currentUser.getMatKhau());
//        if (!isMatched) {
//            return ChangePasswordMessage.FAILED_MATKHAUCU.getMessage();
//        }
//        if (!taiKhoanDto.getMatKhauMoi()
//                .equals(taiKhoanDto.getXacNhanMatKhau())) {
//            return ChangePasswordMessage.FAILED_XACNHANMATKHAU.getMessage();
//        }
//        return ChangePasswordMessage.VALID_CREDENTIALS.getMessage();
//    }
//
//    @Override
//    public byte deleteTaiKhoan(TaiKhoanDto taiKhoanDto) throws SQLException {
//        byte rowEffected = 0;
//        var isTaiKhoanExisted = this.isTaiKhoanExisted(taiKhoanDto);
//        if (isTaiKhoanExisted) {
//            //Delete taikhoan
//            rowEffected = (byte) taiKhoanMapper.deleteTaiKhoan(taiKhoanDto);
//        }
//        return rowEffected;
//    }
//}
