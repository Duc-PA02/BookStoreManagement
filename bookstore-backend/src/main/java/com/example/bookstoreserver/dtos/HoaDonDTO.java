package com.example.bookstoreserver.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonDTO {
    @JsonProperty("ten_hoa_don")
    private String tenHoaDon;
    @JsonProperty("ngay_tao")
    private LocalDateTime ngayTao;
    @JsonProperty("tong_tien")
    private double tongTien;
    @JsonProperty("loai_thanh_toan")
    private String loaiThanhToan;

    @JsonProperty("trang_thai")
    private Boolean trangThai; //thuộc về admin
    @JsonProperty("nguoidung_id")
    private Long nguoiDungId;
}
