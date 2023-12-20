package com.example.BookStore.dao;

import com.example.BookStore.dto.BangDonHangDTO;
import com.example.BookStore.dto.BangSanPhamDTO;
import com.example.BookStore.entity.DonHang;
import com.example.BookStore.entity.DonHangChiTiet;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DonHangDAO implements DAO<DonHang> {
    private EntityManager entityManager;

    @Autowired
    public DonHangDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<BangDonHangDTO> getAll() {
        TypedQuery<DonHang> donHangTypedQuery = entityManager.createQuery("SELECT dh FROM DonHang dh", DonHang.class);
        return donHangTypedQuery.getResultList()
                .stream()
                .map(donHang -> new BangDonHangDTO(donHang, -1))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void save(DonHang donHang) {
        entityManager.persist(donHang);
    }

    public DonHang TimKiemDonHangTheoID(int id){
        TypedQuery<DonHang> donHangTypedQuery = entityManager.createQuery("SELECT dh FROM DonHang dh WHERE dh.maDatHang=:x", DonHang.class);
        donHangTypedQuery.setParameter("x", id);
        return donHangTypedQuery.getSingleResult();
    }

    public BangDonHangDTO timDonHangTheoIdDeHienThi(String id) {
        TypedQuery<DonHang> donHangTypedQuery = entityManager.createQuery("SELECT dh FROM DonHang dh WHERE dh.maDatHang=:x", DonHang.class);
        donHangTypedQuery.setParameter("x", id);
        return new BangDonHangDTO(donHangTypedQuery.getSingleResult(), 1);
    }

    @Override
    @Transactional
    public void update(DonHang donHang) {
        entityManager.merge(donHang);
        entityManager.flush();
    }

    @Transactional
    public void xoaDonHangTheoID(int id){
        DonHang donHangToRemove = TimKiemDonHangTheoID(id);
        if (donHangToRemove != null){
            entityManager.remove(donHangToRemove);
        }
    }
}
