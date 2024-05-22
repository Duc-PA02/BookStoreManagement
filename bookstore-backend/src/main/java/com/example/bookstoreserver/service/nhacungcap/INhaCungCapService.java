package com.example.bookstoreserver.service.nhacungcap;

import com.example.bookstoreserver.dtos.NhaCungCapDTO;
import com.example.bookstoreserver.dtos.NhaCungCapThongKeDTO;
import com.example.bookstoreserver.entity.NhaCungCap;
import com.example.bookstoreserver.exceptions.DataNotFoundException;

import java.util.List;

public interface INhaCungCapService {
    List<NhaCungCapThongKeDTO> thongKeNhaCungCapTheoSoLuongNhap();
    NhaCungCap getNhaCungCapById(Long id) throws DataNotFoundException;
    NhaCungCap createNhaCungCap(NhaCungCapDTO nhaCungCapDTO);
    NhaCungCap updateNhaCungCap(Long id, NhaCungCapDTO nhaCungCapDTO) throws DataNotFoundException;
}
