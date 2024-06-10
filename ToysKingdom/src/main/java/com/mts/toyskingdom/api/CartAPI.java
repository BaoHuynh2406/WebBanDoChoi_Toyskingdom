package com.mts.toyskingdom.api;

import com.mts.toyskingdom.data.dto.CartItemDTO;
import com.mts.toyskingdom.data.mgt.ResponseObject;
import com.mts.toyskingdom.data.model.OrderM;
import com.mts.toyskingdom.service.CartSv;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api-public/cart")
public class CartAPI {
    final CartSv sv;


    @PostMapping("/addToCart")
    public ResponseObject<?> doPostAddToCart(@RequestBody CartItemDTO cartItemDTO) {
        var resultApi = new ResponseObject<>();
        try {
            if (sv.addToCart(cartItemDTO)) {
                resultApi.setSuccess(true);
                resultApi.setMessage("Đã thêm vào giỏ hàng");
                return resultApi;
            }
            resultApi.setSuccess(false);
            resultApi.setMessage("Không thể thêm vào giỏ hàng");
        } catch (Exception e) {
            resultApi.setSuccess(false);
            resultApi.setMessage("Lấy thông tin thất bại");
            log.error("Fail When: ", e);
        }
        return resultApi;
    }


    @GetMapping("/getCart")
    public ResponseObject<?> doGetCart(@RequestParam("idUser") int idUser) {
        var resultApi = new ResponseObject<>();
        try {
            resultApi.setData(sv.getCartByIdUser(idUser));
            resultApi.setSuccess(true);
            resultApi.setMessage("Lấy giỏ hàng thành công");
        } catch (Exception e) {
            resultApi.setSuccess(false);
            resultApi.setMessage("Lấy thông tin thất bại");
            log.error("Fail When: ", e);
        }
        return resultApi;
    }


}
