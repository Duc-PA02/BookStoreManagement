package com.example.bookstoreserver.repository;

import com.example.bookstoreserver.dtos.TongTienBanDuocDTO;
import com.example.bookstoreserver.dtos.TongTienBanDuocThangDTO;
import com.example.bookstoreserver.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Long> {
    @Query("SELECT new com.example.bookstoreserver.dtos.TongTienBanDuocDTO(DATE(hd.ngayTao), SUM(sp.giaBan * sp.soLuong)) " +
            "FROM HoaDon hd " +
            "JOIN hd.danhSachSanPham sp " +
            "WHERE hd.trangThai = true " +
            "GROUP BY DATE(hd.ngayTao) " +
            "ORDER BY DATE(hd.ngayTao)")
    List<TongTienBanDuocDTO> findTotalSalesByDay();

    @Query("SELECT new com.example.bookstoreserver.dtos.TongTienBanDuocThangDTO(YEAR(hd.ngayTao), MONTH(hd.ngayTao), SUM(sp.giaBan * sp.soLuong)) " +
            "FROM HoaDon hd " +
            "JOIN hd.danhSachSanPham sp " +
            "WHERE hd.trangThai = true AND MONTH(hd.ngayTao) = :thang AND (:nam IS NULL OR YEAR(hd.ngayTao) = :nam) " +
            "GROUP BY YEAR(hd.ngayTao), MONTH(hd.ngayTao) " +
            "ORDER BY YEAR(hd.ngayTao), MONTH(hd.ngayTao)")
    List<TongTienBanDuocThangDTO> findTotalSalesByMonthAndYear(Integer thang, Integer nam);
}
