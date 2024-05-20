package com.example.bookstoreserver.service.hoadon;

import com.example.bookstoreserver.dtos.ThongKeRequest;
import com.example.bookstoreserver.dtos.TongTienBanDuocDTO;
import com.example.bookstoreserver.dtos.TongTienBanDuocThangDTO;
import com.example.bookstoreserver.repository.HoaDonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HoaDonService implements IHoaDonService{
    private final HoaDonRepository hoaDonRepository;

    @Override
    public List<TongTienBanDuocDTO> getTotalSalesByDay() {
        return hoaDonRepository.findTotalSalesByDay();
    }

    @Override
    public List<TongTienBanDuocThangDTO> getTotalSalesByMonthAndYear(ThongKeRequest request) {
        // Lấy năm hiện tại nếu không có năm trong request
        Integer nam = (request.getNam() == null) ? LocalDate.now().getYear() : request.getNam();
        // Đảm bảo tháng không được để trống
        if (request.getThang() == null) {
            throw new IllegalArgumentException("Tháng không được để trống");
        }
        return hoaDonRepository.findTotalSalesByMonthAndYear(request.getThang(), nam);
    }
}
