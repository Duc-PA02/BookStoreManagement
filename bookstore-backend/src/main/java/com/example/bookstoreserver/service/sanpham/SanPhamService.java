package com.example.bookstoreserver.service.sanpham;

import com.example.bookstoreserver.dtos.SanPhamDTO;
import com.example.bookstoreserver.entity.DanhMucSanPham;
import com.example.bookstoreserver.entity.HoaDon;
import com.example.bookstoreserver.entity.SanPham;
import com.example.bookstoreserver.exceptions.DataNotFoundException;
import com.example.bookstoreserver.repository.DanhMucSanPhamRepository;
import com.example.bookstoreserver.repository.HoaDonRepository;
import com.example.bookstoreserver.repository.SanPhamRepository;
import com.example.bookstoreserver.responses.sanpham.SanPhamResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SanPhamService implements ISanPhamService{
    private final SanPhamRepository sanPhamRepository;
    private final HoaDonRepository hoaDonRepository;
    private final DanhMucSanPhamRepository danhMucSanPhamRepository;
    @Override
    @Transactional
    public SanPham createSanPham(SanPhamDTO sanPhamDTO) throws Exception {
        DanhMucSanPham existDMSP = danhMucSanPhamRepository.findById(sanPhamDTO.getDanhMucSanPhamId()).orElse(null);
        if (existDMSP == null){
            throw new DataNotFoundException("DMSP khong ton tai");
        }
        SanPham sanPham = SanPham.builder()
                .tenSanPham(sanPhamDTO.getTenSanPham())
                .soLuong(sanPhamDTO.getSoLuong())
                .giaBan(sanPhamDTO.getGiaBan())
                .moTa(sanPhamDTO.getMoTa())
                .imgHero(sanPhamDTO.getImgHero())
                .hoaDon(null)
                .danhMucSanPham(existDMSP)
                .ngayTao(sanPhamDTO.getNgayTao())
                .ngayCapNhat(sanPhamDTO.getNgayCapNhat())
                .build();
        return sanPhamRepository.save(sanPham);
    }

    @Override
    @Transactional
    public SanPham updateSanPham(Long id, SanPhamDTO sanPhamDTO) throws Exception {
        SanPham existSanPham = sanPhamRepository.findById(id).orElseThrow(() -> new DataNotFoundException("San Pham khong ton tai"));
        if (existSanPham != null){
            DanhMucSanPham danhMucSanPham = danhMucSanPhamRepository.findById(sanPhamDTO.getDanhMucSanPhamId()).orElseThrow(()->new DataNotFoundException("DMSP khong ton tai"));
            existSanPham.setTenSanPham(sanPhamDTO.getTenSanPham());
            existSanPham.setSoLuong(sanPhamDTO.getSoLuong());
            existSanPham.setGiaBan(sanPhamDTO.getGiaBan());
            existSanPham.setMoTa(sanPhamDTO.getMoTa());
            existSanPham.setImgHero(sanPhamDTO.getImgHero());
            existSanPham.setHoaDon(null);
            existSanPham.setDanhMucSanPham(danhMucSanPham);
            existSanPham.setNgayTao(sanPhamDTO.getNgayTao());
            existSanPham.setNgayCapNhat(sanPhamDTO.getNgayCapNhat());
        }
        return sanPhamRepository.save(existSanPham);
    }

    @Override
    @Transactional
    public void deleteSanPham(Long id) {
        SanPham sanPham = sanPhamRepository.findById(id).orElse(null);
        if (sanPham != null){
            sanPhamRepository.delete(sanPham);
        }
    }

    @Override
    public Page<SanPhamResponse> getAllSanPhams(PageRequest pageRequest) {
        Page<SanPham> sanPhamPage = sanPhamRepository.findAll(pageRequest);
        return sanPhamPage.map(SanPhamResponse::fromSanPham);
    }
}
