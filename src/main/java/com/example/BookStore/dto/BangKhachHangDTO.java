package com.example.BookStore.dto;

import com.example.BookStore.entity.KhachHang;
import lombok.Data;

@Data
public class BangKhachHangDTO {
    private Integer maKhachHang;
    private String diaChi;
    private String email;
    private String soDienThoai;
    private String tenKhachHang;

    public BangKhachHangDTO(KhachHang khachHang) {
        this.maKhachHang = khachHang.getMaKhachHang();
        this.diaChi = khachHang.getDiaChi();
        this.email = khachHang.getEmail();
        this.soDienThoai = khachHang.getSoDienThoai();
        this.tenKhachHang = khachHang.getTenKhachHang();
    }
}
