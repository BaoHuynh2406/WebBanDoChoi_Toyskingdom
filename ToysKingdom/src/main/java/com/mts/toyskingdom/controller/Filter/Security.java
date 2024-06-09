package com.mts.toyskingdom.controller.Filter;

import com.mts.toyskingdom.data.model.UserM;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = { "/admin/*" })
public class Security implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Kiểm tra xem request có phải là request tới trang admin không
        if (isAdminPage(httpRequest)) {
            // Lấy thông tin từ session nếu người dùng đã đăng nhập
            HttpSession session = httpRequest.getSession(false);
            if (session != null && session.getAttribute("user") != null) {
                // Kiểm tra quyền của người dùng
                UserM user = (UserM) session.getAttribute("user");
                if ("ADMIN".equals(user.getRole())) {
                    // In ra console thông tin của người dùng
                    System.out.println(":(( : " + user.getEmail());
                }
            }
        }

        // Tiếp tục xử lý request
        chain.doFilter(request, response);
    }

    private boolean isAdminPage(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return requestURI.startsWith("/admin/");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo Filter
    }

    @Override
    public void destroy() {
        // Hủy Filter
    }
}
