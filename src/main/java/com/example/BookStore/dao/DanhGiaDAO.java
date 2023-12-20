package com.example.BookStore.dao;

import com.example.BookStore.entity.DanhGia;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DanhGiaDAO implements DAO<DanhGia> {
    private EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(DanhGiaDAO.class);

    @Autowired
    public DanhGiaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<DanhGia> getAll() {
        TypedQuery<DanhGia> query = entityManager.createQuery("SELECT dg FROM DanhGia dg", DanhGia.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void save(DanhGia danhGia) {
        entityManager.persist(danhGia);
        logger.info("Đã thêm một đánh giá: {}", danhGia);
    }

    public DanhGia findDanhGiaById(int id){
        TypedQuery<DanhGia> danhGiaTypedQuery = entityManager.createQuery("SELECT dg FROM DanhGia dg WHERE dg.maDanhGia=:x", DanhGia.class);
        danhGiaTypedQuery.setParameter("x", id);
        List<DanhGia> danhGias = danhGiaTypedQuery.getResultList();
        return danhGias.isEmpty() ? null : danhGias.get(0);
    }
    @Override
    @Transactional
    public void update(DanhGia danhGia) {
        entityManager.merge(danhGia);
        entityManager.flush();
    }

    @Transactional
    public void deleteDanhGiaById(int id) throws EntityNotFoundException {
        DanhGia danhGiaToRemove = findDanhGiaById(id);
        if (danhGiaToRemove != null) {
            entityManager.remove(danhGiaToRemove);
            logger.info("Đã xóa một đánh giá: {}", danhGiaToRemove);
        } else {
            throw new EntityNotFoundException("Không tìm thấy đánh giá với ID: " + id);
        }
    }

}
