package com.example.BookStore.dto;

import com.example.BookStore.entity.SanPham;
import com.example.BookStore.entity.TheLoai;
import lombok.Data;

import java.util.List;
@Data
public class BangTheLoaiDTO {
    private Integer maTheLoai;
    private String tenTheLoai;
    private Integer trangThaiTheLoai;

    public BangTheLoaiDTO(TheLoai theLoai) {
        this.maTheLoai = theLoai.getMaTheLoai();
        this.tenTheLoai = theLoai.getTenTheLoai();
        this.trangThaiTheLoai = theLoai.getTrangThaiTheLoai();

    }

}
