package com.mts.ToysKingdom.controller;

import com.mts.ToysKingdom.data.entity.productE;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import com.mts.ToysKingdom.mapper.productMapper;

import java.util.List;

@Controller
@Service
@RequiredArgsConstructor
public class HomeController {

    final productMapper pMapper;

    @GetMapping("/index")
    public String index(){
        List<productE> products = pMapper.getAllProducts();;
        System.out.println(products);
        return "index";
    }
}
