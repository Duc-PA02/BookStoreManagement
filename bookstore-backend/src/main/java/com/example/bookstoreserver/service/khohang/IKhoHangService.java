package com.example.bookstoreserver.service.khohang;


import com.example.bookstoreserver.dtos.KhoHangDTO;
import com.example.bookstoreserver.dtos.TongTienBanDuocThangDTO;
import com.example.bookstoreserver.entity.KhoHang;

import java.util.List;

public interface IKhoHangService {
    KhoHang getKhoHangById(Long id);
}
