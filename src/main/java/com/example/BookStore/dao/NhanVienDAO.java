package com.example.BookStore.dao;

import com.example.BookStore.dto.BangNhanVienDTO;
import com.example.BookStore.entity.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class NhanVienDAO implements DAO<NhanVien> {
    private EntityManager entityManager;

    @Autowired
    public NhanVienDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //Lấy danh sách tất cả nhân viên
    public List<BangNhanVienDTO> getAll() {
        TypedQuery<NhanVien> nhanVienTypedQuery = entityManager.createQuery("SELECT nv FROM NhanVien nv", NhanVien.class);

        return nhanVienTypedQuery.getResultList()
                .stream()
                .map(nhanVien -> new BangNhanVienDTO(nhanVien))
                .collect(Collectors.toList());
    }

    //Thêm mới một nhân viên
    @Override
    @Transactional
    public void save(NhanVien nhanVien) {
        entityManager.persist(nhanVien);
    }

    //Hàm tìm kiếm nhân viên theo ID để hiển thị
    public BangNhanVienDTO timNhanVienTheoIDDeHienThi(int id){
        TypedQuery<NhanVien> nhanVienTypedQuery = entityManager.createQuery("SELECT nv FROM NhanVien nv WHERE nv.maNhanVien=:x", NhanVien.class);
        nhanVienTypedQuery.setParameter("x", id);

        return new BangNhanVienDTO(nhanVienTypedQuery.getSingleResult());
    }

    //Hàm tìm kiếm nhân viên theo tên để hiển thị
    public List<BangNhanVienDTO> timNhanVienTheoTenDeHienThi(String tenNhanVien){
        TypedQuery<NhanVien> nhanVienTypedQuery = entityManager.createQuery("SELECT nv FROM NhanVien nv WHERE nv.tenNhanVien LIKE :x", NhanVien.class);
        nhanVienTypedQuery.setParameter("x", "%" + tenNhanVien + "%");

        return nhanVienTypedQuery.getResultList()
                .stream()
                .map(BangNhanVienDTO::new)
                .collect(Collectors.toList());
    }

    //Tìm kiếm nhân viên theo id
    public NhanVien timKiemNhanVienTheoID(int id) {
        TypedQuery<NhanVien> nhanVienTypedQuery = entityManager.createQuery("SELECT nv FROM NhanVien nv WHERE nv.maNhanVien=:x", NhanVien.class);
        nhanVienTypedQuery.setParameter("x", id);
        List<NhanVien> nhanViens = nhanVienTypedQuery.getResultList();
        return nhanViens.isEmpty() ? null : nhanViens.get(0);
    }

    //Hàm cập nhật nhân viên
    @Override
    @Transactional
    public void update(NhanVien nhanVien) {
        entityManager.merge(nhanVien);
        entityManager.flush();
    }

    //Hàm xoa nhân viên theo id
    @Transactional
    public void xoaNhanVienTheoID(int id) {
        NhanVien nhanVienToRemove = timKiemNhanVienTheoID(id);
        if (nhanVienToRemove != null) {
            entityManager.remove(nhanVienToRemove);
        }
    }

    //Hàm trả về danh sách nhân viên theo chức vụ
    public List<BangNhanVienDTO> dsNhanVienTheoChucVu(int chucVu) {
        TypedQuery<NhanVien> nhanVienTypedQuery = entityManager.createQuery("SELECT nv FROM NhanVien nv WHERE nv.chucVu=:x", NhanVien.class);
        nhanVienTypedQuery.setParameter("x", chucVu);

        return nhanVienTypedQuery.getResultList()
                .stream()
                .map(BangNhanVienDTO::new)
                .collect(Collectors.toList());
    }
}
