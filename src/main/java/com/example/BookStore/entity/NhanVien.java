package com.example.BookStore.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "bang_nhan_vien")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_nhan_vien")
    private int maNhanVien;

    @Column(name = "ten_nhan_vien")
    private String tenNhanVien;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "so_cccd")
    private String soCCCD;

    @Column(name = "hinh_anh_cccd")
    private String hinhAnhCCCD;

    @Column(name = "gioi_tinh")
    private boolean gioiTinh;

    @Column(name = "chuc_vu")
    private int chucVu;

    @Column(name = "dia_chi")
    private String diaChi;

    public NhanVien() {
    }

    public NhanVien(String tenNhanVien, String soDienThoai, String soCCCD, String hinhAnhCCCD, boolean gioiTinh, int chucVu, String diaChi) {
        this.tenNhanVien = tenNhanVien;
        this.soDienThoai = soDienThoai;
        this.soCCCD = soCCCD;
        this.hinhAnhCCCD = hinhAnhCCCD;
        this.gioiTinh = gioiTinh;
        this.chucVu = chucVu;
        this.diaChi = diaChi;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getSoCCCD() {
        return soCCCD;
    }

    public void setSoCCCD(String soCCCD) {
        this.soCCCD = soCCCD;
    }

    public String getHinhAnhCCCD() {
        return hinhAnhCCCD;
    }

    public void setHinhAnhCCCD(String hinhAnhCCCD) {
        this.hinhAnhCCCD = hinhAnhCCCD;
    }

    public boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getChucVu() {
        return chucVu;
    }

    public void setChucVu(int chucVu) {
        this.chucVu = chucVu;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return "NhanVien{" +
                "maNhanVien=" + maNhanVien +
                ", tenNhanVien='" + tenNhanVien + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", soCCCD='" + soCCCD + '\'' +
                ", hinhAnhCCCD='" + hinhAnhCCCD + '\'' +
                ", gioiTinh=" + gioiTinh +
                ", chucVu='" + chucVu + '\'' +
                ", diaChi='" + diaChi + '\'' +
                '}';
    }
}
