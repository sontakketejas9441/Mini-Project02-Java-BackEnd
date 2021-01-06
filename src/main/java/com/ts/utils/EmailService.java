package com.ts.utils;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.MimeMessageHelper;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;


    public boolean sendMail(String subject, String body, String to)throws MessagingException, IOException{

        boolean isSent = false;

        try{

        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
              
            helper.setSubject(subject);
            helper.setText(body);
            helper.setTo(to);

            mailSender.send(msg);

            isSent = true;

        }catch(Exception e){
            e.printStackTrace();
        }
        
        return isSent;

    }
}
