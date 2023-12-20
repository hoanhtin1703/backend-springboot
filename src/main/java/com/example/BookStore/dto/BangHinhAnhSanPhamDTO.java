package com.example.BookStore.dto;

import com.example.BookStore.entity.HinhAnhSanPham;
import lombok.Data;

@Data
public class BangHinhAnhSanPhamDTO {
    private Integer maHinhAnh;
    private String duongDan;
//    private Integer maSanPham;

    public BangHinhAnhSanPhamDTO(HinhAnhSanPham hinhAnh) {
        this.maHinhAnh = hinhAnh.getMaHinhAnh();
        this.duongDan = hinhAnh.getDuongDan();
    }
}



