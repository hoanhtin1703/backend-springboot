package com.example.BookStore.dto;

import com.example.BookStore.entity.NhanVien;
import lombok.Data;

@Data
public class BangNhanVienDTO {
    private Integer maNhanVien;
    private Integer chucVu;
    private String diaChi;
    private Boolean gioiTinh;
    private String hinhAnhCccd;
    private String soCccd;
    private String soDienThoai;
    private String tenNhanVien;

    public BangNhanVienDTO(NhanVien nhanVien) {
        this.maNhanVien = nhanVien.getMaNhanVien();
        this.chucVu = nhanVien.getChucVu();
        this.diaChi = nhanVien.getDiaChi();
        this.gioiTinh = nhanVien.getGioiTinh();
        this.hinhAnhCccd = nhanVien.getHinhAnhCCCD();
        this.soCccd = nhanVien.getSoCCCD();
        this.soDienThoai = nhanVien.getSoDienThoai();
        this.tenNhanVien = nhanVien.getTenNhanVien();
    }



}
