package com.example.BookStore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.awt.image.ImageProducer;
import java.util.List;

@Entity
@Table(name = "bang_the_loai")
public class TheLoai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_the_loai")
    private int maTheLoai;

    @Column(name = "ten_the_loai")
    private String tenTheLoai;

    @Column(name = "trang_thai_the_loai")
    private int trangThaiTheLoai;

    @OneToMany(mappedBy = "theLoai",
            cascade = CascadeType.ALL)

    private List<SanPham> sanPhams;

    public TheLoai() {
    }

    public TheLoai(String tenTheLoai, int trangThaiTheLoai) {
        this.tenTheLoai = tenTheLoai;
        this.trangThaiTheLoai = trangThaiTheLoai;
    }

    public int getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(int maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public int getTrangThaiTheLoai() {
        return trangThaiTheLoai;
    }

    public void setTrangThaiTheLoai(int trangThaiTheLoai) {
        this.trangThaiTheLoai = trangThaiTheLoai;
    }
    public List<SanPham> getSanPhams() {
        return sanPhams;
    }

    public void setSanPhams(List<SanPham> sanPhams) {
        this.sanPhams = sanPhams;
    }


}
