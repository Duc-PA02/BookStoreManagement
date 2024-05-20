package com.example.bookstoreserver.controller;

import com.example.bookstoreserver.dtos.NhaCungCapThongKeDTO;
import com.example.bookstoreserver.service.nhacungcap.NhaCungCapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("nhacungcap")
public class NhaCungCapController {
    private final NhaCungCapService nhaCungCapService;
    @GetMapping("/thongke")
    @PreAuthorize("hasRole('QUANLY')")
    public ResponseEntity<List<NhaCungCapThongKeDTO>> thongKeNhaCungCapTheoSoLuongNhap() {
        List<NhaCungCapThongKeDTO> thongKeList = nhaCungCapService.thongKeNhaCungCapTheoSoLuongNhap();
        return ResponseEntity.ok(thongKeList);
    }
}
