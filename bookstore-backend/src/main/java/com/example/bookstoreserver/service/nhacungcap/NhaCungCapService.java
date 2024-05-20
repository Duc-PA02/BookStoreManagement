package com.example.bookstoreserver.service.nhacungcap;

import com.example.bookstoreserver.dtos.NhaCungCapThongKeDTO;
import com.example.bookstoreserver.repository.NhaCungCapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NhaCungCapService implements INhaCungCapService{
    private final NhaCungCapRepository nhaCungCapRepository;
    public List<NhaCungCapThongKeDTO> thongKeNhaCungCapTheoSoLuongNhap() {
        return nhaCungCapRepository.thongKeNhaCungCapTheoSoLuongNhap();
    }
}
