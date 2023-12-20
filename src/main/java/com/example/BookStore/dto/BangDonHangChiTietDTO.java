package com.example.BookStore.dto;

import com.example.BookStore.entity.DonHangChiTiet;
import lombok.Data;

@Data
public class BangDonHangChiTietDTO {
    private Integer maDonHangChiTiet;
    private Long gia;
    private Integer soLuong;

    private BangSanPhamDTO sanPhamDTO;

    private BangDonHangDTO bangDonHangDTO;

    public BangDonHangChiTietDTO(DonHangChiTiet d, int choose) {
        this.maDonHangChiTiet = d.getMaDonHangChiTiet();
        this.gia = d.getGia();
        this.soLuong = d.getSoLuong();

        if(choose == 1) {
            this.sanPhamDTO = new BangSanPhamDTO(d.getSanPham(), -1);
        }
        if(choose == 2) {
            this.sanPhamDTO = new BangSanPhamDTO(d.getSanPham(), -1);
            this.bangDonHangDTO = new BangDonHangDTO(d.getDonHang(), -1);
        }
    }
}
