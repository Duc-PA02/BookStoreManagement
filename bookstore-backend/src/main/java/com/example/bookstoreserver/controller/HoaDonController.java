package com.example.bookstoreserver.controller;

import com.example.bookstoreserver.dtos.HoaDonDTO;
import com.example.bookstoreserver.dtos.ThongKeRequest;
import com.example.bookstoreserver.dtos.TongTienBanDuocDTO;
import com.example.bookstoreserver.dtos.TongTienBanDuocThangDTO;
import com.example.bookstoreserver.entity.HoaDon;
import com.example.bookstoreserver.service.hoadon.HoaDonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    @PreAuthorize("hasRole('QUANLY')")
    public List<TongTienBanDuocThangDTO> getTotalSalesByMonthAndYear(@RequestBody ThongKeRequest request) {
        return hoaDonService.getTotalSalesByMonthAndYear(request);
    }
    @PostMapping("/tao-hoa-don")
    @PreAuthorize("hasRole('NHANVIEN')")
    public ResponseEntity<?> createHoaDon(@RequestBody HoaDonDTO hoaDonDTO) throws Exception {
        HoaDon hoaDon = hoaDonService.createHoaDon(hoaDonDTO);
        return ResponseEntity.ok().body(hoaDon);
    }
    @PutMapping("/update-hoadon")
    @PreAuthorize("hasRole('NHANVIEN')")
    public ResponseEntity<?> updateHoaDon(@RequestParam Long id, @RequestBody HoaDonDTO hoaDonDTO) throws Exception {
        HoaDon hoaDon = hoaDonService.updateHoaDon(id, hoaDonDTO);
        return ResponseEntity.ok().body(hoaDon);
    }
}
