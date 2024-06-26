package com.example.bookstoreserver.service.email;

import com.example.bookstoreserver.entity.Email;
import com.example.bookstoreserver.exceptions.ConfirmEmailExpired;
import com.example.bookstoreserver.exceptions.DataNotFoundException;
import com.example.bookstoreserver.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService implements IEmailService{
    private final EmailRepository emailRepository;
    private final JavaMailSender javaMailSender;

    @Override
    public void sendConfirmEmail(String email) {
        Email confirmEmail = Email.builder()
                .nguoiDung(null)
                .code(generateConfirmCode())
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusSeconds(120))
                .isComfirm(false)
                .build();
        emailRepository.save(confirmEmail);

        String subject = "Book Store";
        String content = "Mã xác nhận của bạn là: " + confirmEmail.getCode();
        sendEmail(email, subject, content);
    }

    @Override
    public boolean confirmEmail(String confirmCode) throws Exception {
        Email confirmEmail = emailRepository.findEmailByCode(confirmCode);
        if (confirmEmail == null){
            throw new DataNotFoundException("mã xác minh không chính xác");
        }
        if (isExpired(confirmEmail)){
            throw new ConfirmEmailExpired("mã xác minh đã hết hạn");
        }
        confirmEmail.setComfirm(true);
        emailRepository.save(confirmEmail);
        return true;
    }

    private String generateConfirmCode() {
        Random random = new Random();
        int randomNumber = random.nextInt(900000) + 100000;
        return String.valueOf(randomNumber);
    }

    public void sendEmail(String to, String subject, String content){
        SimpleMailMessage msg =new SimpleMailMessage();
        msg.setFrom("ducpa2002@gmail.com");
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(content);
        javaMailSender.send(msg);
    }

    public boolean isExpired(Email confirmEmail) {
        return LocalDateTime.now().isAfter(confirmEmail.getEndTime());
    }
}
