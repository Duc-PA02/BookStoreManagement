package com.example.bookstoreserver.controller;

import com.example.bookstoreserver.dtos.SanPhamDTO;
import com.example.bookstoreserver.dtos.TieuChiPhanLoaiDTO;
import com.example.bookstoreserver.entity.SanPham;
import com.example.bookstoreserver.exceptions.DataNotFoundException;
import com.example.bookstoreserver.responses.sanpham.SanPhamListResponse;
import com.example.bookstoreserver.responses.sanpham.SanPhamResponse;
import com.example.bookstoreserver.service.sanpham.SanPhamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("sanpham")
public class SanPhamController {
    private final SanPhamService sanPhamService;
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('QUANLY')")
    public ResponseEntity<?> getSanPhamById(@PathVariable Long id) throws DataNotFoundException {
        SanPham sanPham = sanPhamService.getSanPhamById(id);
        return ResponseEntity.ok().body(sanPham);
    }
    @GetMapping("/all-sanpham")
    public ResponseEntity<SanPhamListResponse> getAllSanPhams(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit){
        // Tạo Pageable từ thông tin trang và giới hạn
        PageRequest pageRequest = PageRequest.of(
                page, limit,
                //Sort.by("createdAt").descending()
                Sort.by("id").ascending()
        );
        Page<SanPhamResponse> sanPhamPage = sanPhamService.getAllSanPhams(pageRequest);
        int tongSoPage = sanPhamPage.getTotalPages();
        List<SanPhamResponse> sanPhamResponses = sanPhamPage.getContent();
        return ResponseEntity.ok(SanPhamListResponse
                .builder()
                .sanPhamResponses(sanPhamResponses)
                .tongSoPage(tongSoPage)
                .build());
    }
    @PostMapping
    public ResponseEntity<?> createSanPham(@RequestBody SanPhamDTO sanPhamDTO) throws Exception {
        try {
            SanPham sanPham = sanPhamService.createSanPham(sanPhamDTO);
            return ResponseEntity.ok().body(sanPham);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping
    public ResponseEntity<?> updateSanPham(@RequestParam Long id, @RequestBody SanPhamDTO sanPhamDTO) throws Exception {
        try {
            SanPham sanPham = sanPhamService.updateSanPham(id,sanPhamDTO);
            return ResponseEntity.ok().body(sanPham);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping
    public ResponseEntity<String> deleteSanPham(@RequestParam Long id) throws Exception {
        try {
            sanPhamService.deleteSanPham(id);
            return ResponseEntity.ok().body(String.format("Xoa thanh cong san pham co id = %d", id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/tim-theo-tieu-chi")
    public ResponseEntity<List<SanPham>> timSanPhamTheoTieuChiPhanLoai(@RequestBody TieuChiPhanLoaiDTO tieuChiPhanLoaiDTO) {
        List<SanPham> danhSachSanPham = sanPhamService.timSanPhamTheoTieuChiPhanLoai(tieuChiPhanLoaiDTO);
        return ResponseEntity.ok(danhSachSanPham);
    }
}
