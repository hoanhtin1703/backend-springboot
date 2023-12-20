package com.example.BookStore.dao;

import com.example.BookStore.entity.DonHangChiTiet;
import com.example.BookStore.entity.ThanhToan;
import com.example.BookStore.entity.TheLoai;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ThanhToanDAO implements DAO<ThanhToan> {
    private EntityManager entityManager;

    @Autowired
    public ThanhToanDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<ThanhToan> getAll() {
        TypedQuery<ThanhToan> thanhToanTypedQuery = entityManager.createQuery("SELECT tt FROM ThanhToan tt", ThanhToan.class);
        return thanhToanTypedQuery.getResultList();
    }

    @Override
    @Transactional
    public void save(ThanhToan thanhToan) {
        entityManager.persist(thanhToan);
    }

    public ThanhToan findThanhToanById(int id){
        TypedQuery<ThanhToan> thanhToanTypedQuery = entityManager.createQuery("SELECT tt FROM ThanhToan tt WHERE tt.maThanhToan=:x", ThanhToan.class);
        thanhToanTypedQuery.setParameter("x", id);
        List<ThanhToan> thanhToans = thanhToanTypedQuery.getResultList();
        return thanhToans.isEmpty() ? null : thanhToans.get(0);
    }

    @Override
    @Transactional
    public void update(ThanhToan thanhToan) {
        entityManager.merge(thanhToan);
        entityManager.flush();
    }

    @Transactional
    public void deleteThanhToanById(int id){
        ThanhToan thanhToanToRemove = findThanhToanById(id);
        if (thanhToanToRemove != null){
            entityManager.remove(thanhToanToRemove);
        }
    }
}
