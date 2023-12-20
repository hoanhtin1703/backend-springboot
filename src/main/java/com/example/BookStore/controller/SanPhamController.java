package com.example.BookStore.controller;

import com.example.BookStore.dao.SanPhamDAO;
import com.example.BookStore.dto.BangSanPhamDTO;
import com.example.BookStore.entity.SanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/san-pham")
public class SanPhamController {
    private SanPhamDAO sanPhamDAO;

    @Autowired
    public SanPhamController(SanPhamDAO sanPhamDAO){
        this.sanPhamDAO = sanPhamDAO;
    }

    @GetMapping("")
    public List<BangSanPhamDTO> hienThiDSSP(){
        return sanPhamDAO.getAll();
    }

    @GetMapping("/{id}")
    public BangSanPhamDTO laySanPhamTheoId(@PathVariable("id") int maSanPham){
        return sanPhamDAO.timSanPhamTheoIdDeHienThi(maSanPham);
    }
    @GetMapping("/sanphammoi")
    public List<BangSanPhamDTO> Sanphammoi(){
        return  sanPhamDAO.getsanphammoi();
    }

    @GetMapping("/tim-kiem")
    public List<BangSanPhamDTO> laySanPhamTheoTen(@RequestParam(name = "ten") String tenSanPham){
        return sanPhamDAO.timSanPhamBangTen(tenSanPham);
    }

    @PostMapping("/them")
    public String themSanPham(@RequestBody SanPham sanPham){
        sanPham.setTheSanPham(sanPhamDAO.removeUnikey(sanPham.getTenSanPham()));

        sanPhamDAO.save(sanPham);
        return "Bạn đã thêm thành công!";
    }

    @PutMapping("/cap-nhat")
    public String capNhatSanPham(@RequestBody SanPham sanPham){
        sanPhamDAO.update(sanPham);
        return "Bạn đã cập nhật thành công!";
    }

    @DeleteMapping("/xoa/{id}")
    public String xoaSanPham(@PathVariable("id") int id){
        sanPhamDAO.deleteSanPhamById(id);
        return "Bạn đã xóa thành công!";
    }

    @GetMapping("/the-loai/{id}")
    public List<BangSanPhamDTO> laySanPhamTheoTheLoai(@PathVariable("id") int maTheLoai){
        return sanPhamDAO.timKiemSanPhamTheoTheLoai(maTheLoai);
    }

    @GetMapping("/gia")
    public List<BangSanPhamDTO> laySanPhamTheoGia(@RequestParam("min") double minPrice, @RequestParam("max") double maxPrice){
        return sanPhamDAO.timKiemSanPhamTheoGia(minPrice, maxPrice);
    }

    @GetMapping("/so-luong")
    public Long soLuongSanPham(){
        return sanPhamDAO.count();
    }
}
