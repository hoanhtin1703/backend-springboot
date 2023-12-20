package com.example.BookStore.dto;

import com.example.BookStore.entity.ThumnailSanPham;

import lombok.Data;
@Data
public class BangThumnailSanPhamDTO {

    private String duongDan;
//    private Integer maSanPham;

    public BangThumnailSanPhamDTO(ThumnailSanPham hinhAnh) {
        this.duongDan = hinhAnh.getDuongDan();
    }
}
