package com.example.BookStore.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bang_thum_nail_san_pham")
public class ThumnailSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_thum_nail")
    private int maHinhAnh;

    @Column(name = "ma_san_pham")
    private int maSanPham;

    @Column(name = "duong_dan")
    private String duongDan;
    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "ma_san_pham", insertable = false, updatable = false)
    private SanPham sanPham;

    public ThumnailSanPham(int maSanPham, String duongDan) {
        this.maSanPham = maSanPham;
        this.duongDan = duongDan;
    }

    public ThumnailSanPham(String duongDan, SanPham sanPham) {
        this.duongDan = duongDan;
        this.sanPham = sanPham;
    }

    public ThumnailSanPham(int maHinhAnh, int maSanPham, String duongDan, SanPham sanPham) {
        this.maHinhAnh = maHinhAnh;
        this.maSanPham = maSanPham;
        this.duongDan = duongDan;
        this.sanPham = sanPham;
    }

    public ThumnailSanPham() {
    }

    public int getMaHinhAnh() {
        return maHinhAnh;
    }

    public void setMaHinhAnh(int maHinhAnh) {
        this.maHinhAnh = maHinhAnh;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getDuongDan() {
        return duongDan;
    }

    public void setDuongDan(String duongDan) {
        this.duongDan = duongDan;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }
}
