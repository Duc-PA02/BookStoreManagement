package com.example.bookstoreserver.service.nguoidung;

import com.example.bookstoreserver.dtos.NguoiDungDTO;
import com.example.bookstoreserver.entity.NguoiDung;
import com.example.bookstoreserver.entity.Role;
import com.example.bookstoreserver.exceptions.DataIntegrityViolationException;
import com.example.bookstoreserver.exceptions.DataNotFoundException;
import com.example.bookstoreserver.repository.NguoiDungRepository;
import com.example.bookstoreserver.repository.RoleRepository;
import com.example.bookstoreserver.responses.nguoidung.ChangePasswordRequest;
import com.example.bookstoreserver.responses.nguoidung.ForgotPasswordRequest;
import com.example.bookstoreserver.responses.nguoidung.LoginRequest;
import com.example.bookstoreserver.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NguoiDungService implements INguoiDungService{
    private final NguoiDungRepository nguoiDungRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final EmailService emailService;
    @Override
    public String login(LoginRequest loginRequest) throws Exception {
        return null;
    }

    @Override
    public String register(NguoiDungDTO nguoiDungDTO) throws Exception {
        String email = nguoiDungDTO.getEmail();
        if (nguoiDungRepository.existsByEmail(email)){
            throw new DataIntegrityViolationException("email đã tồn tại");
        }
        emailService.sendConfirmEmail(email);
        return "Mã xác minh đã được gửi đến email của bạn";
    }

    @Override
    public void saveUser(NguoiDungDTO nguoiDungDTO) throws Exception {
        String email = nguoiDungDTO.getEmail();
        if (nguoiDungRepository.existsByEmail(email)){
            throw new Exception("email da ton tai");
        }
        Role role = roleRepository.findById(3L).orElseThrow(()->new IllegalStateException("Khong tim thay role"));
        NguoiDung nguoiDungMoi = NguoiDung.builder()
                .email(nguoiDungDTO.getEmail())
                .matKhau(nguoiDungDTO.getMatKhau())
                .hoTen(nguoiDungDTO.getHoTen())
                .diaChi(nguoiDungDTO.getDiaChi())
                .soDienThoai(nguoiDungDTO.getSoDienThoai())
                .ngaySinh(nguoiDungDTO.getNgaySinh())
                .role(role)
                .build();
        String matkhau = nguoiDungDTO.getMatKhau();
        String encodeMatKhau = passwordEncoder.encode(matkhau);
        nguoiDungMoi.setMatKhau(encodeMatKhau);
    }

    @Override
    public String changePassword(int userId, ChangePasswordRequest changePasswordRequest) throws Exception {
        return null;
    }

    @Override
    public String forgotPassword(ForgotPasswordRequest forgotPasswordRequest) throws Exception {
        return null;
    }
}
