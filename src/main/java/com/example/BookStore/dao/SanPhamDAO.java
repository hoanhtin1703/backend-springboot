package com.example.BookStore.dao;

import java.text.Normalizer;
import java.util.regex.Pattern;

import com.example.BookStore.dto.BangSanPhamDTO;
import com.example.BookStore.entity.HinhAnhSanPham;
import com.example.BookStore.entity.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SanPhamDAO implements DAO<SanPham> {
    private EntityManager entityManager;

    @Autowired
    public SanPhamDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<BangSanPhamDTO> getAll() {
        TypedQuery<SanPham> sanPhamTypedQuery = entityManager.createQuery("SELECT sp FROM SanPham sp", SanPham.class);
        ;
        return sanPhamTypedQuery.getResultList()
                .stream()
                .map(sanPham -> new BangSanPhamDTO(sanPham, -1))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void save(SanPham sanPham) {
        entityManager.persist(sanPham);
    }

    @Override
    @Transactional
    public void update(SanPham sanPham) {
        entityManager.merge(sanPham);
        entityManager.flush();
    }

    @Transactional
    public void deleteSanPhamById(int id) {
        SanPham sanPhamToRemove = timSanPhamTheoId(id);
        if (sanPhamToRemove != null) {
            entityManager.remove(sanPhamToRemove);
        }
    }

    public BangSanPhamDTO timSanPhamTheoIdDeHienThi(int id) {
        TypedQuery<SanPham> sanPhamTypedQuery = entityManager.createQuery("SELECT sp FROM SanPham sp WHERE sp.maSanPham=:x", SanPham.class);
        sanPhamTypedQuery.setParameter("x", id);
        return new BangSanPhamDTO(sanPhamTypedQuery.getSingleResult(), 1);
    }
    public List<BangSanPhamDTO> getsanphammoi() {
        TypedQuery<SanPham> sanPhamTypedQuery = entityManager.createQuery("SELECT sp FROM SanPham sp ORDER BY sp.maSanPham DESC", SanPham.class);
        ;
        sanPhamTypedQuery.setMaxResults(5);
        return sanPhamTypedQuery.getResultList()
                .stream()
                .map(sanPham -> new BangSanPhamDTO(sanPham, -1))
                .collect(Collectors.toList());
    }

    public SanPham timSanPhamTheoId(int id) {
        TypedQuery<SanPham> sanPhamTypedQuery = entityManager.createQuery("SELECT sp FROM SanPham sp WHERE sp.maSanPham=:x", SanPham.class);
        sanPhamTypedQuery.setParameter("x", id);
        SanPham spm = sanPhamTypedQuery.getSingleResult();
        return spm;
    }

    // Hàm xóa dấu tiếng Việt
    public String removeUnikey(String tenSanPham) {
        if (tenSanPham == null) {
            return null; // hoặc có thể trả về chuỗi rỗng tùy thuộc vào yêu cầu
        }

        String decomposed = Normalizer.normalize(tenSanPham.toLowerCase(), Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String result = pattern.matcher(decomposed).replaceAll("");

        // Kiểm tra chuỗi sau khi xử lý
        return result.isEmpty() ? null : result.replace("đ", "d");
    }

    // Hàm tìm sản phẩm theo tên, hỗ trợ xóa dấu và khoảng trắng
    public List<BangSanPhamDTO> timSanPhamBangTen(String tenSanPham) {
        if (tenSanPham == null || tenSanPham.trim().isEmpty()) {
            return null;
        }

        String cleanTenSanPham = removeUnikey(tenSanPham);
        if (cleanTenSanPham.isEmpty()) {
            return null;
        }

        TypedQuery<SanPham> sanPhamTypedQuery = entityManager.createQuery(
                "SELECT sp FROM SanPham sp WHERE sp.theSanPham LIKE :x", SanPham.class);
        sanPhamTypedQuery.setParameter("x", "%" + cleanTenSanPham + "%");

        List<SanPham> resultList = sanPhamTypedQuery.getResultList();

        return resultList
                .stream()
                .map(sanPham -> new BangSanPhamDTO(sanPham, -1))
                .collect(Collectors.toList());

    }

    //Tìm sản phẩm theo Thể loại
    public List<BangSanPhamDTO> timKiemSanPhamTheoTheLoai(int maTheLoai) {
        TypedQuery<SanPham> sanPhamTypedQuery = entityManager.createQuery("SELECT sp FROM SanPham sp WHERE sp.theLoai.maTheLoai=:x", SanPham.class);
        sanPhamTypedQuery.setParameter("x", maTheLoai);
        return sanPhamTypedQuery.getResultList()
                .stream()
                .map(sanPham -> new BangSanPhamDTO(sanPham, -1))
                .collect(Collectors.toList());
    }

    //Tìm sản phẩm theo Nhà cung cấp
//    public List<BangSanPhamDTO> timKiemTheoMaNhaCungCap(int maNhaCungCap) {
//        TypedQuery<SanPham> sanPhamTypedQuery = entityManager.createQuery("SELECT sp FROM SanPham sp WHERE sp.maNhaCungCap = :x", SanPham.class);
//        sanPhamTypedQuery.setParameter("x", maNhaCungCap);
//        return sanPhamTypedQuery.getResultList()
//                .stream()
//                .map(sanPham -> new BangSanPhamDTO(sanPham, -1))
//                .collect(Collectors.toList());
//    }

    //Tìm sản phẩm theo khoảng giá
    public List<BangSanPhamDTO> timKiemSanPhamTheoGia(double minPrice, double maxPrice) {
        TypedQuery<SanPham> sanPhamTypedQuery = entityManager.createQuery("SELECT sp FROM SanPham sp WHERE sp.giaBan BETWEEN :minPrice AND :maxPrice", SanPham.class);
        sanPhamTypedQuery.setParameter("minPrice", minPrice);
        sanPhamTypedQuery.setParameter("maxPrice", maxPrice);
        return sanPhamTypedQuery.getResultList()
                .stream()
                .map(sanPham -> new BangSanPhamDTO(sanPham, -1))
                .collect(Collectors.toList());
    }

    // Đếm số lượng sản phẩm có trong cơ sở dữ liệu
    public long count() {
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(sp) FROM SanPham sp", Long.class);
        return query.getSingleResult();
    }

}
