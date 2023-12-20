package com.example.BookStore.dao;

import com.example.BookStore.dto.BangKhachHangDTO;
import com.example.BookStore.dto.BangNhanVienDTO;
import com.example.BookStore.dto.BangSanPhamDTO;
import com.example.BookStore.entity.KhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class KhachHangDAO implements DAO<KhachHang> {
    private EntityManager entityManager;

    @Autowired
    public KhachHangDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<BangKhachHangDTO> getAll() {
        TypedQuery<KhachHang> khachHangTypedQuery = entityManager.createQuery("SELECT kh FROM KhachHang kh", KhachHang.class);
        return khachHangTypedQuery.getResultList()
                .stream()
                .map(BangKhachHangDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void save(KhachHang khachHang) {
        entityManager.persist(khachHang);
    }

    //Tìm kiếm khách hàng theo ID
    public KhachHang timKiemKhachHangTheoID(int id) {
        TypedQuery<KhachHang> khachHangTypedQuery = entityManager.createQuery("SELECT kh FROM KhachHang kh WHERE kh.maKhachHang=:x", KhachHang.class);
        khachHangTypedQuery.setParameter("x", id);
        List<KhachHang> khachHangs = khachHangTypedQuery.getResultList();
        return khachHangs.isEmpty() ? null : khachHangs.get(0);
    }

    //Tìm kiếm khách hàng theo tên để hiển thị
    public List<BangKhachHangDTO> timKiemKhachHangTheoTen(String tenKhachHang){
        TypedQuery<KhachHang> khachHangTypedQuery = entityManager.createQuery("SELECT kh FROM KhachHang kh WHERE kh.tenKhachHang LIKE :x", KhachHang.class);
        khachHangTypedQuery.setParameter("x", "%" + tenKhachHang + "%");

        return khachHangTypedQuery.getResultList()
                .stream()
                .map(BangKhachHangDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void update(KhachHang khachHang) {
        entityManager.merge(khachHang);
        entityManager.flush();
    }

    //Xóa khách hàng theo ID
    @Transactional
    public void xoaKhachHangTheoID(int id) {
        KhachHang khachHangToRemove = timKiemKhachHangTheoID(id);
        if (khachHangToRemove != null) {
            entityManager.remove(khachHangToRemove);
        }
    }
}
