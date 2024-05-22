package com.example.bookstoreserver.dtos;

import com.example.bookstoreserver.entity.PhieuNhap;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NhaCungCapThongKeDTO {
    @JsonProperty("ten_nha_cung_cap")
    private String tenNhaCungCap;
    @JsonProperty("tong_so_luong_nhap")
    private int tongSoLuongNhap;
}
