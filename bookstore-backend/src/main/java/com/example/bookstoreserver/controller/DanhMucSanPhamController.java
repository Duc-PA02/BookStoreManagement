package com.example.bookstoreserver.controller;

import com.example.bookstoreserver.dtos.DanhMucSanPhamDTO;
import com.example.bookstoreserver.entity.DanhMucSanPham;
import com.example.bookstoreserver.service.danhmucsanpham.DanhMucSanPhamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("danh-muc-san-pham")
public class DanhMucSanPhamController {
    private final DanhMucSanPhamService danhMucSanPhamService;
    @GetMapping("/all")
    @PreAuthorize("hasRole('QUANLY')")
    public ResponseEntity<?> getAllDanhMuc(){
        try {
            List<DanhMucSanPhamDTO> danhMucSanPhamList = danhMucSanPhamService.getAllDanhMuc();
            return ResponseEntity.ok().body(danhMucSanPhamList);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
