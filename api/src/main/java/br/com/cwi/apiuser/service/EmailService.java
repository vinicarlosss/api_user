package br.com.cwi.apiuser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Value("${MAIL_USER}")
    private String supportEmail;

    public void sendEmailToClient(String subject, String email, String content) throws MessagingException{

        MimeMessage mail = mailSender.createMimeMessage();

        MimeMessageHelper message = new MimeMessageHelper(mail);
        message.setSubject(subject);
        message.setText(content, true);
        message.setFrom(supportEmail);
        message.setTo(email);

        mailSender.send(mail);
    }

    public String getContent(String token) {
        return "<p>Olá , aqui está o seu token para alteração de senha: </p>" +
                token;
    }
}
