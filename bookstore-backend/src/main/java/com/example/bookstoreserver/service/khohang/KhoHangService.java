package com.example.bookstoreserver.service.khohang;

import com.example.bookstoreserver.dtos.DanhMucSanPhamDTO;
import com.example.bookstoreserver.dtos.KhoHangDTO;
import com.example.bookstoreserver.entity.KhoHang;
import com.example.bookstoreserver.repository.KhoHangRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KhoHangService implements IKhoHangService{
    private final KhoHangRepository khoHangRepository;
    private final ModelMapper modelMapper;

    @Override
    public KhoHang getKhoHangById(Long id) {
        KhoHang khoHang = khoHangRepository.getKhoHangById(id);
        return khoHang;
    }
}
