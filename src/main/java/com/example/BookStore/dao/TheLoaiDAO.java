package com.example.BookStore.dao;

import com.example.BookStore.dto.BangTheLoaiDTO;
import com.example.BookStore.entity.NhanVien;
import com.example.BookStore.entity.TheLoai;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TheLoaiDAO implements DAO<TheLoai> {
    private EntityManager entityManager;

    @Autowired
    public TheLoaiDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<BangTheLoaiDTO> getAll() {
        TypedQuery<TheLoai> theLoaiTypedQuery = entityManager.createQuery("SELECT tl FROM TheLoai tl", TheLoai.class);
        return theLoaiTypedQuery.getResultList()
                .stream()
                .map(BangTheLoaiDTO::new)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public void save(TheLoai theLoai) {
        entityManager.persist(theLoai);

    }

    public TheLoai findTheLoaiById(int id) {
        TypedQuery<TheLoai> theLoaiTypedQuery = entityManager.createQuery("SELECT tl FROM TheLoai tl WHERE tl.maTheLoai=:x", TheLoai.class);
        theLoaiTypedQuery.setParameter("x", id);
        List<TheLoai> theLoais = theLoaiTypedQuery.getResultList();
        return theLoais.isEmpty() ? null : theLoais.get(0);
    }

    @Override
    @Transactional
    public void update(TheLoai theLoai) {
        entityManager.merge(theLoai);
        entityManager.flush();
    }

    @Transactional
    public void deleteTheLoaiById(int id) {
        TheLoai theLoaiToRemove = findTheLoaiById(id);
        if (theLoaiToRemove != null) {
            entityManager.remove(theLoaiToRemove);
        }
    }
}
