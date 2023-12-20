package com.example.BookStore.dao;

import com.example.BookStore.entity.DonHangChiTiet;
import com.example.BookStore.entity.NhaCungCap;
import com.example.BookStore.entity.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NhaCungCapDAO implements DAO<NhaCungCap> {
    private EntityManager entityManager;

    @Autowired
    public NhaCungCapDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<NhaCungCap> getAll() {
        TypedQuery<NhaCungCap> nhaCungCapTypedQuery = entityManager.createQuery("SELECT ncc FROM NhaCungCap ncc", NhaCungCap.class);
        return nhaCungCapTypedQuery.getResultList();
    }

    @Override
    @Transactional
    public void save(NhaCungCap nhaCungCap) {
        entityManager.persist(nhaCungCap);
    }

    public NhaCungCap findNhaCungCapById(int id) {
        TypedQuery<NhaCungCap> nhaCungCapTypedQuery = entityManager.createQuery("SELECT ncc FROM NhaCungCap ncc WHERE ncc.maNhaCungCap=:x", NhaCungCap.class);
        nhaCungCapTypedQuery.setParameter("x", id);
        List<NhaCungCap> nhaCungCaps = nhaCungCapTypedQuery.getResultList();
        return nhaCungCaps.isEmpty() ? null : nhaCungCaps.get(0);
    }

    @Override
    @Transactional
    public void update(NhaCungCap nhaCungCap) {
        entityManager.merge(nhaCungCap);
        entityManager.flush();
    }

    @Transactional
    public void deleteNhaCungCapById(int id) {
        NhaCungCap nhaCungCapToRemove = findNhaCungCapById(id);
        if (nhaCungCapToRemove != null) {
            entityManager.remove(nhaCungCapToRemove);
        }
    }


}
