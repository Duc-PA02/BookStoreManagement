package com.example.bookstoreserver.controller;

import com.example.bookstoreserver.dtos.NguoiDungDTO;
import com.example.bookstoreserver.entity.Email;
import com.example.bookstoreserver.repository.EmailRepository;
import com.example.bookstoreserver.service.email.EmailService;
import com.example.bookstoreserver.service.nguoidung.NguoiDungService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("nguoidung")
@RequiredArgsConstructor
public class NguoiDungController {
    private final NguoiDungService nguoiDungService;
    private final EmailService emailService;
    private final EmailRepository emailRepository;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody NguoiDungDTO nguoiDungDTO) throws Exception{
        try {
            String msg = nguoiDungService.register(nguoiDungDTO);
            return ResponseEntity.ok().body(msg);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/confirm-register")
    public ResponseEntity<?> confirmRegister(@RequestParam String confirmCode, @RequestBody NguoiDungDTO nguoiDungDTO) throws Exception{
        try {
            boolean isConfirm = emailService.confirmEmail(confirmCode);
            Email confirmEmail = emailRepository.findEmailByCode(confirmCode);
            if (isConfirm){
                nguoiDungService.saveUser(nguoiDungDTO);
                emailRepository.delete(confirmEmail);
            }
            return ResponseEntity.ok().body("Đăng ký thành công");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
