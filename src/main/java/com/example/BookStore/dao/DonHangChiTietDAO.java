package com.example.BookStore.dao;

import com.example.BookStore.dto.BangDonHangChiTietDTO;
import com.example.BookStore.dto.BangDonHangDTO;
import com.example.BookStore.entity.DonHangChiTiet;
import com.example.BookStore.entity.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DonHangChiTietDAO implements DAO<DonHangChiTiet> {
    private EntityManager entityManager;

    @Autowired
    public DonHangChiTietDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(DonHangChiTiet donHangChiTiet) {
        entityManager.persist(donHangChiTiet);
    }


    public List<BangDonHangChiTietDTO> getAll() {
        TypedQuery<DonHangChiTiet> donHangChiTietTypedQuery = entityManager.createQuery("SELECT dhct FROM DonHangChiTiet dhct", DonHangChiTiet.class);
        return donHangChiTietTypedQuery.getResultList()
                .stream()
                .map(donHangChiTiet -> new BangDonHangChiTietDTO(donHangChiTiet, 1))
                .collect(Collectors.toList());
    }

    public DonHangChiTiet timKiemDonHangChiTiet(int id){
        TypedQuery<DonHangChiTiet> donHangChiTietTypedQuery = entityManager.createQuery("SELECT dhct FROM DonHangChiTiet dhct WHERE dhct.maDonHangChiTiet=:x", DonHangChiTiet.class);
        donHangChiTietTypedQuery.setParameter("x", id);
        return donHangChiTietTypedQuery.getSingleResult();
    }

    @Override
    @Transactional
    public void update(DonHangChiTiet donHangChiTiet) {
        entityManager.merge(donHangChiTiet);
        entityManager.flush();
    }

    @Transactional
    public void xoaDonHangChiTiet(int id){
        DonHangChiTiet donHangChiTietToRemove = timKiemDonHangChiTiet(id);
        if (donHangChiTietToRemove != null){
            entityManager.remove(donHangChiTietToRemove);
        }
    }

    public BangDonHangChiTietDTO timDonHangChiTietDeHienThi(int id){
        TypedQuery<DonHangChiTiet> donHangChiTietTypedQuery = entityManager.createQuery("SELECT dhct FROM DonHangChiTiet dhct WHERE dhct.maDonHangChiTiet=:x", DonHangChiTiet.class);
        donHangChiTietTypedQuery.setParameter("x", id);
        return new BangDonHangChiTietDTO(donHangChiTietTypedQuery.getSingleResult(), 1);
    }
}
