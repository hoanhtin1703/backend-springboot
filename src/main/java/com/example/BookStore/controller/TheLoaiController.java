package com.example.BookStore.controller;

import com.example.BookStore.dao.TheLoaiDAO;
import com.example.BookStore.dto.BangTheLoaiDTO;
import com.example.BookStore.entity.TheLoai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/the-loai")
public class TheLoaiController {
    private TheLoaiDAO theLoaiDAO;

    @Autowired
    public TheLoaiController(TheLoaiDAO theLoaiDAO){
        this.theLoaiDAO = theLoaiDAO;
    }

    @GetMapping("")
    public List<BangTheLoaiDTO> layDanhSachTheLoai(TheLoai theLoai){
        return theLoaiDAO.getAll();
    }

    @PostMapping("/them")
    public String themTheLoai(@RequestBody TheLoai theLoai){
        theLoaiDAO.save(theLoai);
        return "Thêm thành công!";
    }

    @PutMapping("/cap-nhat")
    public String capNhatTheLoai(@RequestBody TheLoai theLoai){
        theLoaiDAO.update(theLoai);
        return "Cập nhật thành công!";
    }

    @DeleteMapping("/xoa/{id}")
    public String xoaTheLoai(@PathVariable("id") int id){
        theLoaiDAO.deleteTheLoaiById(id);
        return "Xóa thành công!";
    }

    @GetMapping("/id/{id}")
    public String layTheLoaiTheoID(@PathVariable("id") int id){
        theLoaiDAO.findTheLoaiById(id);
        return "Lấythanfhh công thể loại có id là: " + id;
    }
}
