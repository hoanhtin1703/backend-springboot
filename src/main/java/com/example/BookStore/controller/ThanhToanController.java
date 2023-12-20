package com.example.BookStore.controller;

import com.example.BookStore.dao.ThanhToanDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/thanh-toan")
public class  ThanhToanController {
    private ThanhToanDAO thanhToanDAO;

    @Autowired
    public ThanhToanController(ThanhToanDAO thanhToanDAO){
        this.thanhToanDAO = thanhToanDAO;
    }
}
