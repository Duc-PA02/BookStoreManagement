package com.example.bookstoreserver.repository;

import com.example.bookstoreserver.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SanPhamRepository extends JpaRepository<SanPham, Long> {
}
