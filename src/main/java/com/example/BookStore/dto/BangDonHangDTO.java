package com.example.BookStore.dto;

import com.example.BookStore.entity.DonHang;
import com.example.BookStore.entity.DonHangChiTiet;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class BangDonHangDTO {
    private String maDatHang;
    private String diaChiGiaoHang;
    private String ngayDatHang;
    private String ngayGiaoHang;
    private String soDienThoaiNhanVien;
    private int maNhanVien;
    private String tenNhanVien;
    private Long tongTien;
    private Integer trangThaiDonHang;
    private BangKhachHangDTO khachHang;
    private List<BangDonHangChiTietDTO> donHangChiTiet;
    private String phuongThucThanhToan;

    public BangDonHangDTO(DonHang donHang, int choose) {
        this.maDatHang = donHang.getMaDatHang();
        this.diaChiGiaoHang = donHang.getDiaChiGiaoHang();
        this.ngayDatHang = donHang.getNgayDatHang();
        this.ngayGiaoHang = donHang.getNgayGiaoHang();
//        this.maNhanVien = donHang.getMaNhanVien();
//        this.tenNhanVien = donHang.getTenNhanVien();
        this.tongTien = donHang.getTongTien();
        this.trangThaiDonHang = donHang.getTrangThaiDonHang();
        this.phuongThucThanhToan = donHang.getThanhToan().getPhuongThucThanhToan();
        this.khachHang = new BangKhachHangDTO(donHang.getKhachHang());
        // Choose == 1 dùng khi xem chi tiết về đơn hàng
        if(choose == 1) {
            this.donHangChiTiet = donHang
                    .getDonHangChiTiets()
                    .stream()
                    .map(sanPham -> new BangDonHangChiTietDTO(sanPham, 1))
                    .collect(Collectors.toList());
        }

    }

}
