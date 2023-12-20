package com.example.BookStore.controller;

import com.example.BookStore.dao.KhachHangDAO;
import com.example.BookStore.dto.BangKhachHangDTO;
import com.example.BookStore.dto.BangNhanVienDTO;
import com.example.BookStore.entity.KhachHang;
import com.example.BookStore.entity.NhanVien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/khach-hang")
public class KhachHangController {
    private KhachHangDAO khachHangDAO;

    @Autowired
    public KhachHangController(KhachHangDAO khachHangDAO) {
        this.khachHangDAO = khachHangDAO;
    }

    @GetMapping("")
    public List<BangKhachHangDTO> hienThiDSKH(KhachHang khachHang){
        return khachHangDAO.getAll();
    }

    @PostMapping("/them")
    public String themKhachHang(@RequestBody KhachHang khachHang){
        khachHangDAO.save(khachHang);
        return "Bạn đã thêm thành công!";
    }

    @PutMapping("/cap-nhat")
    public String capNhatKhachHang(@RequestBody KhachHang khachHang){
        khachHangDAO.update(khachHang);
        return "Bạn đã cập nhật thành công!";
    }

    @DeleteMapping("/xoa/{id}")
    public String xoaKhachHang(@PathVariable("id") int id){
        khachHangDAO.xoaKhachHangTheoID(id);
        return "Bạn đã xóa thành công khách hàng với id là " + id;
    }

    @GetMapping("/thong-tin/{id}")
    public KhachHang thongTinKhachHang(@PathVariable("id") int id) {
        KhachHang khachHang = khachHangDAO.timKiemKhachHangTheoID(id);
        return khachHang;
    }

    @GetMapping("/tim-kiem")
    public List<BangKhachHangDTO> layKhachHangTheoTen(@RequestParam(name = "ten") String tenKhachHang){
        return khachHangDAO.timKiemKhachHangTheoTen(tenKhachHang);
    }
}
