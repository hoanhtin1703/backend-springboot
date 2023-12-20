package com.example.BookStore.dto;

import com.example.BookStore.entity.ThanhToan;
import lombok.Data;

@Data
public class BangThanhToanDTO {
    private Integer maThanhToan;
    private String phuongThucThanhToan;

    public BangThanhToanDTO(ThanhToan thanhToan) {
        this.maThanhToan = thanhToan.getMaThanhToan();
        this.phuongThucThanhToan = thanhToan.getPhuongThucThanhToan();
    }
}
