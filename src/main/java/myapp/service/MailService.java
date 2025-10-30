package myapp.service;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import myapp.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Properties;

@Service
public class MailService {

    private static final Logger log = LoggerFactory.getLogger(MailService.class);

    @Value("${app.mail.enabled:true}")
    private boolean mailEnabled;

    @Value("${spring.mail.host:}")
    private String smtpHost;

    @Value("${spring.mail.port:25}")
    private int smtpPort;

    @Value("${spring.mail.username:}")
    private String smtpUsername;

    @Value("${spring.mail.password:}")
    private String smtpPassword;

    @Value("${spring.mail.properties.mail.smtp.auth:false}")
    private boolean smtpAuth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable:false}")
    private boolean starttlsEnable;

    public void sendEmail(User sender, List<String> recipients, String subject, String text) {
        if (!mailEnabled) {
            log.info("Mail delivery disabled via configuration; skipping email send.");
            return;
        }

        if (smtpHost == null || smtpHost.isBlank()) {
            log.warn("Mail configuration missing host; skipping email send.");
            return;
        }
        if (smtpPort <= 0) {
            log.warn("Mail configuration has invalid port {}; skipping email send.", smtpPort);
            return;
        }

        if (smtpAuth && (smtpUsername == null || smtpUsername.isBlank())) {
            log.warn("Mail configuration requires credentials but username is missing; skipping email send.");
            return;
        }

        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", Objects.toString(smtpPort));
        props.put("mail.smtp.auth", Boolean.toString(smtpAuth));
        props.put("mail.smtp.starttls.enable", Boolean.toString(starttlsEnable));

        Session session;
        if (smtpAuth) {
            session = Session.getInstance(props, new jakarta.mail.Authenticator() {
                protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new jakarta.mail.PasswordAuthentication(smtpUsername, smtpPassword);
                }
            });
        } else {
            session = Session.getInstance(props);
        }

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender.getEmail()));
            for (String recipient : recipients) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            }
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
            log.info("Email sent successfully to {}", recipients);

        } catch (MessagingException e) {
            log.error("Failed to send email", e);
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
