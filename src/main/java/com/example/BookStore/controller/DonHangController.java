package com.example.BookStore.controller;

import com.example.BookStore.dao.DonHangDAO;
import com.example.BookStore.dto.BangDonHangDTO;
import com.example.BookStore.dto.BangSanPhamDTO;
import com.example.BookStore.entity.DonHang;
import com.example.BookStore.entity.SanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/don-hang")
public class DonHangController {
    private DonHangDAO donHangDAO;

    @Autowired
    public DonHangController(DonHangDAO donHangDAO) {
        this.donHangDAO = donHangDAO;
    }


    @GetMapping("")
    public List<BangDonHangDTO> hienThiDSDonHang(){
        return donHangDAO.getAll();
    }

    @PostMapping("/them")
    public ResponseEntity<Object> themSanPham(@RequestBody DonHang donHang) {
        donHangDAO.save(donHang);
        HashMap<String,Object> result = new HashMap<>();
        result.put("message","Thêm đơn hàng thành công");
        result.put("status",true);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/cap-nhat")
    public String capNhatSanPham(@RequestBody DonHang donHang){
        donHangDAO.update(donHang);
        return "Bạn đã cập nhật thành công!";
    }

    @DeleteMapping("/xoa/{id}")
    public String xoaSanPham(@PathVariable("id") int id){
        donHangDAO.xoaDonHangTheoID(id);
        return "Bạn đã xóa thành công!";
    }

    @GetMapping("/{id}")
    public BangDonHangDTO layDonHangTheoID(@PathVariable("id") String maDatHang){
        return donHangDAO.timDonHangTheoIdDeHienThi(maDatHang);
    }
}
