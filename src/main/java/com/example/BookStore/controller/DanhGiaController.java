package com.example.BookStore.controller;

import com.example.BookStore.dao.DanhGiaDAO;
import com.example.BookStore.entity.DanhGia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/danh-gia")
public class DanhGiaController {
    private DanhGiaDAO danhGiaDAO;

    @Autowired
    public DanhGiaController(DanhGiaDAO danhGiaDAO){
        this.danhGiaDAO = danhGiaDAO;
    }



}
