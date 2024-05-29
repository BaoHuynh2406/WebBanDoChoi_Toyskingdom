//package com.mts.ToysKingdom.service;
//
//import com.church.gxtanhung.systemconfigsecurity.security.UserPrincipal;
//import com.church.gxtanhung.tanhung.data.dto.TaiKhoanDto;
//import com.church.gxtanhung.tanhung.data.model.TaiKhoanM;
//
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Map;
//
//public interface TaiKhoanService {
//    /**
//     * Lấy thông tin tenDangNhap, tenThanh, tenNguoiDung, vaiTro, soDienThoai,
//     *                  trangThai, giaoHo, vaiTroGX của tất cả các tài khoản user
//     *
//     * @return Danh sách tài khoản user
//     */
//    List<TaiKhoanM> getAllTaiKhoan() throws SQLException;
//
//    /**
//     * Lấy thông tin tài khoản của người dùng dựa trên UserPrincipal.
//     *
//     * @param userId sys_id của người dùng đang login.
//     * @return Đối tượng TaiKhoanM chứa thông tin tài khoản của người dùng.
//     * @throws Exception Nếu có lỗi xảy ra trong quá trình lấy thông tin tài khoản.
//     */
//    TaiKhoanM getTaiKhoanByUserId(Long userId) throws SQLException;
//
//    /**
//     * @param taiKhoanDto Chứa các thuộc tính cần thiết như : tenDangNhap
//     * @return true nếu tài khoản đã tồn tại và ngược lại
//     * @throws SQLException
//     */
//    Boolean isTaiKhoanExisted(TaiKhoanDto taiKhoanDto) throws SQLException;
//
//    /**
//     * @param taiKhoanDto chua cac thuoc tinh can thiet nhu:
//     *                    tenDangNhap, tenThanh, tenNguoiDung, vaiTro, soDienThoai,
//     *                    trangThai, giaoHo, vaiTroGX
//     * @return
//     * @throws SQLException
//     */
//    byte updateTaiKhoan(TaiKhoanDto taiKhoanDto) throws SQLException, Exception;
//
//    /**
//     * Đổi mật khẩu của người dùng được tìm với tenDangNhap
//     *
//     * @param taiKhoanDto là đối tượng chứa các thuộc tính cần thiết:
//     *                    tenDangNhap, matKhauCu, matKhauMoi, xacNhanMatKhau
//     * @return Message tương ứng với từng trạng thái của việc đổi mật khẩu
//     * tham khảo tại constant/ChangePasswordMessage.java
//     * @throws SQLException
//     */
//    Map<String, Object> changePasswordByUserId(TaiKhoanDto taiKhoanDto, UserPrincipal userPrincipal) throws SQLException;
//
//    /**
//     * @param taiKhoanDto chua cac thuoc tinh can thiet nhu:
//     *                    tenDangNhap để xóa tài khoản
//     * @return
//     * @throws SQLException
//     */
//    byte deleteTaiKhoan(TaiKhoanDto taiKhoanDto) throws SQLException;
//}
