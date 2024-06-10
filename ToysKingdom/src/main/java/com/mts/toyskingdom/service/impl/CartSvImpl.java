package com.mts.toyskingdom.service.impl;

import com.mts.toyskingdom.data.dto.CartItemDTO;
import com.mts.toyskingdom.data.dto.OrderDTO;
import com.mts.toyskingdom.data.dto.OrderItemDTO;
import com.mts.toyskingdom.data.entity.ProductE;
import com.mts.toyskingdom.data.model.CartItemM;
import com.mts.toyskingdom.data.model.OrderM;
import com.mts.toyskingdom.data.model.ProductFeatureM;
import com.mts.toyskingdom.mapper.OrderItemMapper;
import com.mts.toyskingdom.mapper.OrderMapper;
import com.mts.toyskingdom.service.CartSv;
import com.mts.toyskingdom.service.OrderItemSv;
import com.mts.toyskingdom.service.OrderSv;
import com.mts.toyskingdom.service.ProductSv;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartSvImpl implements CartSv {
    final OrderSv orderSv;
    final OrderItemSv orderItemSv;
    final ProductSv productSv;
    final OrderMapper mapper;

    @Override
    public boolean addToCart(CartItemDTO cartItemDTO) throws SQLException {
        try {
            //Tìm đơn hàng của người dùng
            OrderM orderM = orderSv.findPendingByIdUser(cartItemDTO.getIdUser());
            //Nếu có rồi thì bỏ qua, và thêm vào đơn hàng chi tiết
            if (orderM == null) {
                //Nếu không có đơn hàng thì tạo mới một đơn hàng
                OrderDTO orderDTO = new OrderDTO();
                orderDTO.setIdUser(cartItemDTO.getIdUser());
                orderSv.createOrder(orderDTO);
                orderM = orderSv.findPendingByIdUser(cartItemDTO.getIdUser());
            }

            //Kiểm tra đã có đơn hàng chưa
            if (orderM == null) {
                return false;
            }

            //Thêm vào đơn hàng chi tiết
            OrderItemDTO orderItemDTO = new OrderItemDTO();

            orderItemDTO.setIdOrder(orderM.getIdOrder());
            orderItemDTO.setIdProduct(cartItemDTO.getIdProduct());
            orderItemDTO.setOrderQuantity(cartItemDTO.getQuantity());

            //Lấy giá nếu có giảm giá
            //Giá sẽ là giá của 1 sản phẩm
            ProductFeatureM product = productSv.getProductByID(cartItemDTO.getIdProduct());
            orderItemDTO.setPrice(product.getPriceAfterDiscount());

            //Thêm vào đơn hàng chi tiết
            orderItemSv.addOrderDetails(orderItemDTO);
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi chỗ CartSvImpl: " + e.getMessage());
            return false;
        }
    }


    @Override
    public boolean removeFromCart(CartItemDTO cartItemDTO) throws SQLException {
        return false;
    }

    @Override
    public List<CartItemM> getCartByIdUser(int idUser) throws SQLException {
        try {
            //Lấy mã đơn hàng của user
            int idOrder = orderSv.findPendingByIdUser(idUser).getIdOrder();
            return mapper.getOrderItemByIdOrder(idOrder);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean payment(int idUser) throws SQLException {
        try{
            int idOrder = orderSv.findPendingByIdUser(idUser).getIdOrder();
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setIdOrder(idOrder);
            orderDTO.setOrderStatus("PAID");
            if(orderSv.updateOrder(orderDTO) > 0) {
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }

    }

}
