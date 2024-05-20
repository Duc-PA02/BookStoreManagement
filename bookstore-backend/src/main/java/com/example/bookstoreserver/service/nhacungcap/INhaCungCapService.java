package com.example.bookstoreserver.service.nhacungcap;

import com.example.bookstoreserver.dtos.NhaCungCapThongKeDTO;

import java.util.List;

public interface INhaCungCapService {
    List<NhaCungCapThongKeDTO> thongKeNhaCungCapTheoSoLuongNhap();
}
