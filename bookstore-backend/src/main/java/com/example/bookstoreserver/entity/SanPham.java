package com.example.bookstoreserver.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "sanpham")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanPham extends BaseEntity{
    @Column(name = "ten_san_pham")
    private String tenSanPham;
    @Column(name = "so_luong")
    private int soLuong;
    @Column(name = "gia_ban")
    private double giaBan;
    @Column(name = "mo_ta")
    private String moTa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hoadon_id", foreignKey = @ForeignKey(name = "fk_sanpham_hoadon"))
    @JsonBackReference
    private HoaDon hoaDon;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loaisp_id", foreignKey = @ForeignKey(name = "fk_sanpham_danhmucsp"))
    @JsonBackReference
    private DanhMucSanPham danhMucSanPham;

    @OneToMany(mappedBy = "sanPham")
    @JsonManagedReference
    private List<ImageSanPham> danhSachImage;
}
