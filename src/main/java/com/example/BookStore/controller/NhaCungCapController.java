package com.example.BookStore.controller;

import com.example.BookStore.dao.NhaCungCapDAO;
import com.example.BookStore.entity.NhaCungCap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nha-cung-cap")
public class NhaCungCapController {
    private NhaCungCapDAO nhaCungCapDAO;

    @Autowired
    public NhaCungCapController(NhaCungCapDAO nhaCungCapDAO){
        this.nhaCungCapDAO = nhaCungCapDAO;
    }

    @GetMapping("")
    public List<NhaCungCap> layDSNhaCungCap(NhaCungCap nhaCungCap){
        return nhaCungCapDAO.getAll();
    }

    @GetMapping("/id/{id}")
    public String layNhaCungCapTheoID(@PathVariable("id") int id){
        nhaCungCapDAO.findNhaCungCapById(id);
        return "Lấy thành công nhà cung cấp có id là " + id;
    }

    @PostMapping("/them")
    public String themNhaCungCap(@RequestBody NhaCungCap nhaCungCap){
        nhaCungCapDAO.save(nhaCungCap);
        return "Thêm thành công!";
    }

    @PutMapping("/cap-nhat")
    public String capNhatNhaCungCap(@RequestBody NhaCungCap nhaCungCap){
        nhaCungCapDAO.update(nhaCungCap);
        return "Cập nhật thành công!";
    }

    @DeleteMapping("/xoa/{id}")
    public String xoaNhaCungCap(@PathVariable("id") int id){
        nhaCungCapDAO.deleteNhaCungCapById(id);
        return "Xóa thành công!";
    }
}
