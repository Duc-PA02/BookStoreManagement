package com.example.bookstoreserver.controller;

import com.example.bookstoreserver.dtos.KhoHangDTO;
import com.example.bookstoreserver.entity.KhoHang;
import com.example.bookstoreserver.service.khohang.KhoHangService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("kho-hang")
public class KhoHangController {
    private final KhoHangService khoHangService;
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('QUANLY')")
    public ResponseEntity<?> getKhoHangById(@PathVariable Long id){
        try {
            KhoHang khoHang = khoHangService.getKhoHangById(id);
            return ResponseEntity.ok().body(khoHang);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
