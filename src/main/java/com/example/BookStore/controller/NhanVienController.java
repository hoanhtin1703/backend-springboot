package com.example.BookStore.controller;

import com.example.BookStore.dto.BangNhanVienDTO;
import com.example.BookStore.entity.NhanVien;
import com.example.BookStore.dao.NhanVienDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nhan-vien")
public class NhanVienController {
    private NhanVienDAO nhanVienDAO;

    @Autowired
    public NhanVienController(NhanVienDAO nhanVienDAO) {
        this.nhanVienDAO = nhanVienDAO;
    }

    @GetMapping("")
    public List<BangNhanVienDTO> hienThiDanhSachNhanVien() {
        return nhanVienDAO.getAll();
    }

    @PostMapping("/them")
    public String themNhanVien(@RequestBody NhanVien nhanVien) {
        nhanVienDAO.save(nhanVien);
        return "Bạn đã thêm thành công nhân viên: " + nhanVien;
    }

    @GetMapping("/thong-tin/{id}")
    public NhanVien thongTinNhanVien(@PathVariable("id") int id) {
        NhanVien nhanVien = nhanVienDAO.timKiemNhanVienTheoID(id);
        return nhanVien;
    }

    @DeleteMapping("/xoa/{id}")
    public String xoaNhanVien(@PathVariable("id") int id) {
        nhanVienDAO.xoaNhanVienTheoID(id);
        return "Bạn đã xóa thành công nhân viên với id là: " + id;
    }

    @GetMapping("/chuc-vu/{id}")
    public List<BangNhanVienDTO> layDSNVTheoChucVu(@PathVariable("id") int chucVu){
        return nhanVienDAO.dsNhanVienTheoChucVu(chucVu);
    }

    @PutMapping("/cap-nhat")
    public String capNhatNhanVien(@RequestBody NhanVien nhanVien){
        nhanVienDAO.update(nhanVien);
        return "Cập nhật thành công!" + nhanVien;
    }

    @GetMapping("/tim-kiem")
    public List<BangNhanVienDTO> layNhanVienTheoTen(@RequestParam(name = "ten") String tenNhanVien){
        return nhanVienDAO.timNhanVienTheoTenDeHienThi(tenNhanVien);
    }
}
