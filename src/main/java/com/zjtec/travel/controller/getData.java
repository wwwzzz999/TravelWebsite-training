//package com.zjtec.travel.controller;
//
//import com.zjtec.travel.domain.Seller;
//import com.zjtec.travel.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/get")
//public class getData {
//    @Autowired
//    private UserService userService;
//
//    @RequestMapping("/detail")
//    @ResponseBody
//    public String detail(){
//        List<Seller> data=userService.findSeller();
//        System.out.println(data);
//
//        return "123";
//    }
//}
