package com.example.BookStore.dto;

import lombok.Data;

@Data
public class BangSanPhamNhaCungCapDTO {
    private Integer maNhaCungCap;
    private Integer maSanPham;

    public Integer getMaNhaCungCap() {
        return this.maNhaCungCap;
    }

    public void setMaNhaCungCap(Integer maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public Integer getMaSanPham() {
        return this.maSanPham;
    }

    public void setMaSanPham(Integer maSanPham) {
        this.maSanPham = maSanPham;
    }
}
