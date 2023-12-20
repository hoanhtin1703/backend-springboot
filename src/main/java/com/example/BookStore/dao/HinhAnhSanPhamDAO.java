package com.example.BookStore.dao;

import com.example.BookStore.entity.HinhAnhSanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Repository
public class HinhAnhSanPhamDAO implements DAO<HinhAnhSanPham> {
    private EntityManager entityManager;

    @Autowired
    public HinhAnhSanPhamDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<HinhAnhSanPham> getAll() {
        TypedQuery<HinhAnhSanPham> hinhAnhSanPhamTypedQuery = entityManager.createQuery("SELECT hasp FROM HinhAnhSanPham hasp", HinhAnhSanPham.class);
        return hinhAnhSanPhamTypedQuery.getResultList();
    }

    @Override
    @Transactional
    public void save(HinhAnhSanPham hinhAnhSanPham) {
        entityManager.persist(hinhAnhSanPham);
    }

    public HinhAnhSanPham findHinhAnhSanPhamById(int id) {
        TypedQuery<HinhAnhSanPham> hinhAnhSanPhamTypedQuery = entityManager.createQuery("SELECT hasp FROM HinhAnhSanPham hasp WHERE hasp.maHinhAnh=:x", HinhAnhSanPham.class);
        hinhAnhSanPhamTypedQuery.setParameter("x", id);
        List<HinhAnhSanPham> hinhAnhSanPhams = hinhAnhSanPhamTypedQuery.getResultList();
        return hinhAnhSanPhams.isEmpty() ? null : hinhAnhSanPhams.get(0);
    }

    @Override
    @Transactional
    public void update(HinhAnhSanPham hinhAnhSanPham) {
        entityManager.merge(hinhAnhSanPham);
        entityManager.flush();
    }

    @Transactional
    public void deleteHinhAnhSanPhamById(int id) {
        HinhAnhSanPham hinhAnhSanPhamToRemove = findHinhAnhSanPhamById(id);
        if (hinhAnhSanPhamToRemove != null) {
            entityManager.remove(hinhAnhSanPhamToRemove);
        }
    }

    public String dangTaiHinhAnh(String duongDan, MultipartFile file, int maSanPham) {
        String tenAnh = file.getOriginalFilename();
        File f = new File(duongDan);
        if (!f.exists()) {
            f.mkdir();
        }
        String duongDanFile = duongDan + File.separator + tenAnh;
        File fileToCopy = new File(duongDanFile);

        try {
            if(fileToCopy.exists()) {
                fileToCopy.delete();
            }
            System.out.println("F: " + duongDanFile);
            Files.copy(file.getInputStream(), Paths.get(duongDanFile));
        } catch (Exception e) {
            throw new RuntimeException("Không thấy nơi lưu trữ ảnh");
        }
        return tenAnh;
    }

    public InputStream hienThiHinhAnh(String path, String filename) throws IOException {
        String fullpath = path + File.separator + filename;
        System.out.println(fullpath);
        return new FileInputStream(fullpath);
    }
}
