package com.example.BookStore.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bang_don_hang_chi_tiet")
public class DonHangChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_don_hang_chi_tiet")
    private int maDonHangChiTiet;

    @Column(name = "ma_san_pham")
    private int maSanPham;

    @Column(name = "gia")
    private long gia;

    @Column(name = "so_luong")
    private int soLuong;

    @Column(name = "ma_dat_hang")
    private String maDatHang;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    @JoinColumn(name = "ma_san_pham", insertable = false, updatable = false)
    private SanPham sanPham;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    @JoinColumn(name = "ma_dat_hang", insertable = false, updatable = false)
    private DonHang donHang;

    public DonHangChiTiet() {
    }

    public DonHangChiTiet(int maSanPham, long gia, int soLuong) {
        this.maSanPham = maSanPham;
        this.gia = gia;
        this.soLuong = soLuong;
    }

    public int getMaDonHangChiTiet() {
        return maDonHangChiTiet;
    }

    public void setMaDonHangChiTiet(int maDonHangChiTiet) {
        this.maDonHangChiTiet = maDonHangChiTiet;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public DonHang getDonHang() {
        return donHang;
    }

    public void setDonHang(DonHang donHang) {
        this.donHang = donHang;
    }

    public String getMaDatHang() {
        return maDatHang;
    }

    public void setMaDatHang(String maDatHang) {
        this.maDatHang = maDatHang;
    }
}
