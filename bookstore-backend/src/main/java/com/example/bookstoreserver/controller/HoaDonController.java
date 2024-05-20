package com.example.bookstoreserver.controller;

import com.example.bookstoreserver.dtos.ThongKeRequest;
import com.example.bookstoreserver.dtos.TongTienBanDuocDTO;
import com.example.bookstoreserver.dtos.TongTienBanDuocThangDTO;
import com.example.bookstoreserver.service.hoadon.HoaDonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("hoa-don")
public class HoaDonController {
    private final HoaDonService hoaDonService;
    @GetMapping("/thongketheongay")
    @PreAuthorize("hasRole('QUANLY')")
    public List<TongTienBanDuocDTO> getTotalSalesByDay() {
        return hoaDonService.getTotalSalesByDay();
    }
    @PostMapping("/thongke/thang")
    public List<TongTienBanDuocThangDTO> getTotalSalesByMonthAndYear(@RequestBody ThongKeRequest request) {
        return hoaDonService.getTotalSalesByMonthAndYear(request);
    }
}
