package com.example.bookstoreserver.service.danhmucsanpham;

import com.example.bookstoreserver.dtos.DanhMucSanPhamDTO;
import com.example.bookstoreserver.entity.DanhMucSanPham;
import com.example.bookstoreserver.repository.DanhMucSanPhamRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DanhMucSanPhamService implements IDanhMucSanPhamService{
    private final DanhMucSanPhamRepository danhMucSanPhamRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<DanhMucSanPhamDTO> getAllDanhMuc() {
        List<DanhMucSanPham> danhMucSanPhamList = danhMucSanPhamRepository.findAll();
        List<DanhMucSanPhamDTO> danhMucSanPhamDTOS = danhMucSanPhamList.stream()
                .map(danhMucSanPham -> modelMapper.map(danhMucSanPham, DanhMucSanPhamDTO.class))
                .toList();
        return danhMucSanPhamDTOS;
    }
}
