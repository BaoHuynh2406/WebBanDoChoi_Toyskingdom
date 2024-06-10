package com.mts.toyskingdom.controller.interceptor;

import com.mts.toyskingdom.data.model.UserM;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // In ra URL của request
        System.out.println("Request URL: " + request.getRequestURI());

        // Kiểm tra xem request có phải là request tới trang admin không
        if (isAdminPage(request)) {
            System.out.println("Admin");

            // Lấy thông tin từ session nếu người dùng đã đăng nhập
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("user") != null) {
                // Kiểm tra quyền của người dùng
                UserM user = (UserM) session.getAttribute("user");
                if ("ADMIN".equals(user.getRole())) {
                    // Người dùng là ADMIN, tiếp tục xử lý request
                    return true;
                } else {
                    // Người dùng không có quyền ADMIN, chuyển hướng đến trang lỗi hoặc thông báo
                    response.sendRedirect(request.getContextPath() + "/app#!/login");
                    return false;
                }
            } else {
                // Người dùng chưa đăng nhập, chuyển hướng đến trang đăng nhập
                response.sendRedirect(request.getContextPath() + "/app#!/login");
                return false;
            }
        }

        // Nếu không phải request tới trang admin, tiếp tục xử lý request
        return true;
    }

    private boolean isAdminPage(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        boolean isAdmin = requestURI.startsWith(request.getContextPath() + "/admin/");
        // In ra kết quả của phương thức isAdminPage
        System.out.println("isAdminPage: " + isAdmin);
        return isAdmin;
    }
}
