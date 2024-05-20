package com.example.bookstoreserver.service.hoadon;

import com.example.bookstoreserver.dtos.ThongKeRequest;
import com.example.bookstoreserver.dtos.TongTienBanDuocDTO;
import com.example.bookstoreserver.dtos.TongTienBanDuocThangDTO;

import java.util.List;

public interface IHoaDonService {
    List<TongTienBanDuocDTO> getTotalSalesByDay();
    List<TongTienBanDuocThangDTO> getTotalSalesByMonthAndYear(ThongKeRequest thongKeRequest);
}
