package com.example.bookstoreserver.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "nguoidung")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NguoiDung extends BaseEntity{
    @Column(name = "tai_khoan")
    private String taiKhoan;
    @Column(name = "mat_khau")
    private String matKhau;
    @Column(name = "ho_ten")
    private String hoTen;
    @Column(name = "dia_chi")
    private String diaChi;
    @Column(name = "ngay_sinh")
    private Date ngaySinh;
    @Column(name = "so_dien_thoai", length = 10)
    private String soDienThoai;

    @OneToMany(mappedBy = "nguoiDung")
    @JsonManagedReference
    private List<HoaDon> danhSachHoaDon;

    @OneToMany(mappedBy = "nguoiDung")
    @JsonManagedReference
    private List<DanhMucSanPham> danhMucSanPhams;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "fk_nguoidung_role"))
    @JsonBackReference
    private Role role;

    @OneToMany(mappedBy = "nguoiDung")
    @JsonManagedReference
    private List<PhieuNhap> danhSachPhieuNhap;
}
