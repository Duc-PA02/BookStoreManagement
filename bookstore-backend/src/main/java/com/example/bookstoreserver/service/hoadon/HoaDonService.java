package com.example.bookstoreserver.service.hoadon;

import com.example.bookstoreserver.dtos.HoaDonDTO;
import com.example.bookstoreserver.dtos.ThongKeRequest;
import com.example.bookstoreserver.dtos.TongTienBanDuocDTO;
import com.example.bookstoreserver.dtos.TongTienBanDuocThangDTO;
import com.example.bookstoreserver.entity.HoaDon;
import com.example.bookstoreserver.entity.NguoiDung;
import com.example.bookstoreserver.exceptions.DataNotFoundException;
import com.example.bookstoreserver.repository.HoaDonRepository;
import com.example.bookstoreserver.repository.NguoiDungRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HoaDonService implements IHoaDonService{
    private final HoaDonRepository hoaDonRepository;
    private final NguoiDungRepository nguoiDungRepository;
    @Override
    public HoaDon createHoaDon(HoaDonDTO hoaDonDTO) throws Exception {
        NguoiDung nguoiDung = nguoiDungRepository.findById(hoaDonDTO.getNguoiDungId()).orElse(null);
        if (nguoiDung == null){
            throw new DataNotFoundException("Nguoi dung khong ton tai");
        }
        HoaDon hoaDon = HoaDon.builder()
                .tenHoaDon(hoaDonDTO.getTenHoaDon())
                .ngayTao(LocalDate.now())
                .loaiThanhToan(hoaDonDTO.getLoaiThanhToan())
                .tongTien(hoaDonDTO.getTongTien())
                .trangThai(false)
                .nguoiDung(nguoiDung)
                .build();
        return hoaDonRepository.save(hoaDon);
    }

    @Override
    public HoaDon updateHoaDon(Long id, HoaDonDTO hoaDonDTO) throws Exception {
        HoaDon existingHoaDon = hoaDonRepository.findById(id).orElse(null);
        if (existingHoaDon != null){
            NguoiDung nguoiDung = nguoiDungRepository.findById(hoaDonDTO.getNguoiDungId()).orElse(null);
            if (nguoiDung == null){
                throw new DataNotFoundException("Nguoi dung khong ton tai");
            }
            HoaDon hoaDon = HoaDon.builder()
                    .tenHoaDon(hoaDonDTO.getTenHoaDon())
                    .ngayTao(hoaDonDTO.getNgayTao())
                    .loaiThanhToan(hoaDonDTO.getLoaiThanhToan())
                    .tongTien(hoaDonDTO.getTongTien())
                    .trangThai(hoaDonDTO.getTrangThai())
                    .build();
            return hoaDonRepository.save(hoaDon);
        }

        return null;
    }
}
