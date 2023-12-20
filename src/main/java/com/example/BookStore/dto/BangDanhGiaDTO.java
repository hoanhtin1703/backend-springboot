package com.example.BookStore.dto;

import com.example.BookStore.entity.DanhGia;
import com.example.BookStore.entity.KhachHang;
import lombok.Data;

@Data
public class BangDanhGiaDTO {
    private Integer maDanhGia;
    private Integer soSao;
    private String noiDung;
    private Integer trangThai;
    private Integer maSanPham;
    private BangKhachHangDTO khachHang;
    public BangDanhGiaDTO(DanhGia danhGia) {
        this.maDanhGia = danhGia.getMaDanhGia();
        this.soSao = danhGia.getSoSao();
        this.noiDung = danhGia.getText();
        this.trangThai = danhGia.getTrangThai();
        this.khachHang = new BangKhachHangDTO(danhGia.getKhachHang());
    }
}
