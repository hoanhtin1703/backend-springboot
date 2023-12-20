package com.example.BookStore.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "bang_don_hang")
public class DonHang {

    @Id
    @Column(name = "ma_dat_hang")
    private String maDatHang;

    @Column(name = "ma_khach_hang")
    private Integer maKhachHang;

    @Column(name = "ma_nhan_vien")
    private Integer maNhanVien;

    @Column(name = "ngay_dat_hang")
    private String ngayDatHang;

    @Column(name = "ngay_giao_hang")
    private String ngayGiaoHang;

    @Column(name = "dia_chi_giao_hang")
    private String diaChiGiaoHang;

    @Column(name = "ten_nhan_vien")
    private String tenNhanVien;

    @Column(name = "so_dien_thoai_nhan_vien")
    private String soDienThoaiNhanVien;

    @Column(name = "trang_thai_don_hang")
    private int trangThaiDonHang;

    @Column(name = "ma_thanh_toan")
    private int maThanhToan;

    @Column(name = "tong_tien")
    private long tongTien;

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "ma_thanh_toan", insertable = false, updatable = false)
    private ThanhToan thanhToan;

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "ma_khach_hang", insertable = false, updatable = false)
    private KhachHang khachHang;

    @OneToMany(mappedBy = "donHang",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            })
    private List<DonHangChiTiet> donHangChiTiets;


    public DonHang() {
    }

    public DonHang(int maKhachHang, int maNhanVien, String ngayDatHang, String ngayGiaoHang, String diaChiGiaoHang, String tenNhanVien, String soDienThoaiNhanVien, int trangThaiDonHang, int maThanhToan, long tongTien) {
        this.maKhachHang = maKhachHang;
        this.maNhanVien = maNhanVien;
        this.ngayDatHang = ngayDatHang;
        this.ngayGiaoHang = ngayGiaoHang;
        this.diaChiGiaoHang = diaChiGiaoHang;
        this.tenNhanVien = tenNhanVien;
        this.soDienThoaiNhanVien = soDienThoaiNhanVien;
        this.trangThaiDonHang = trangThaiDonHang;
        this.maThanhToan = maThanhToan;
        this.tongTien = tongTien;
    }

    public void setMaDatHang(String maDatHang) {
        this.maDatHang = maDatHang;
    }

    public String getMaDatHang() { return maDatHang; }
    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getNgayDatHang() {
        return ngayDatHang;
    }

    public void setNgayDatHang(String ngayDatHang) {
        this.ngayDatHang = ngayDatHang;
    }

    public String getNgayGiaoHang() {
        return ngayGiaoHang;
    }

    public void setNgayGiaoHang(String ngayGiaoHang) {
        this.ngayGiaoHang = ngayGiaoHang;
    }

    public String getDiaChiGiaoHang() {
        return diaChiGiaoHang;
    }

    public void setDiaChiGiaoHang(String diaChiGiaoHang) {
        this.diaChiGiaoHang = diaChiGiaoHang;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getSoDienThoaiNhanVien() {
        return soDienThoaiNhanVien;
    }

    public void setSoDienThoaiNhanVien(String soDienThoaiNhanVien) {
        this.soDienThoaiNhanVien = soDienThoaiNhanVien;
    }

    public int getTrangThaiDonHang() {
        return trangThaiDonHang;
    }

    public void setTrangThaiDonHang(int trangThaiDonHang) {
        this.trangThaiDonHang = trangThaiDonHang;
    }


    public int getMaThanhToan() {
        return maThanhToan;
    }

    public void setMaThanhToan(int maThanhToan) {
        this.maThanhToan = maThanhToan;
    }

    public long getTongTien() {
        return tongTien;
    }

    public void setTongTien(long tongTien) {
        this.tongTien = tongTien;
    }

    public ThanhToan getThanhToan() {
        return thanhToan;
    }

    public void setThanhToans(ThanhToan thanhToan) {
        this.thanhToan = thanhToan;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHangs(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public List<DonHangChiTiet> getDonHangChiTiets() {
        return donHangChiTiets;
    }

    public void setDonHangChiTiets(List<DonHangChiTiet> donHangChiTiets) {
        this.donHangChiTiets = donHangChiTiets;
    }


}
