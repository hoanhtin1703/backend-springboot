package com.example.BookStore.controller;

import com.example.BookStore.dao.HinhAnhSanPhamDAO;
import com.example.BookStore.entity.FileResponse;
import com.example.BookStore.entity.HinhAnhSanPham;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/hinh-anh")
public class HinhAnhSanPhamController {
    private HinhAnhSanPhamDAO hinhAnhSanPhamDAO;

    @Autowired
    public HinhAnhSanPhamController(HinhAnhSanPhamDAO hinhAnhSanPhamDAO){
        this.hinhAnhSanPhamDAO = hinhAnhSanPhamDAO;
    }

    String duongDanFolder = "images/";

    @PostMapping("/them/{id}")
    public ResponseEntity<FileResponse> dangTaiHinhAnh(@RequestParam("files") MultipartFile[] files, @PathVariable("id") int maSanPham) throws IOException {
        try {
            Arrays.stream(files).forEach(file -> {
                String tenSanPham = hinhAnhSanPhamDAO.dangTaiHinhAnh(duongDanFolder, file, maSanPham);
                hinhAnhSanPhamDAO.save(new HinhAnhSanPham(maSanPham, tenSanPham));
            });
            return ResponseEntity.status(HttpStatus.OK).body(new FileResponse("Thêm ảnh thành công"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileResponse("Thêm ảnh thất bại"));
        }
    }

    @GetMapping("/{image}")
    public void layHinhAnh(@PathVariable("image") String duongDanHinhAnh, HttpServletResponse response) throws IOException {
        InputStream inputStream = hinhAnhSanPhamDAO.hienThiHinhAnh(duongDanFolder, duongDanHinhAnh);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(inputStream,response.getOutputStream());
    }

}
