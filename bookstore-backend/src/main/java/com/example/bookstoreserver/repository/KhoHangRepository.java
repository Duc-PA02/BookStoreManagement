package com.example.bookstoreserver.repository;

import com.example.bookstoreserver.entity.KhoHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhoHangRepository extends JpaRepository<KhoHang, Integer> {
    KhoHang getKhoHangById(Long id);
}
