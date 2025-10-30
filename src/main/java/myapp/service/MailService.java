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
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Properties;

@Service
public class MailService {

    private static final Logger log = LoggerFactory.getLogger(MailService.class);

    private final Environment environment;

    public MailService(Environment environment) {
        this.environment = environment;
    }

    public void sendEmail(User sender, List<String> recipients, String subject, String text) {
        String smtpHost = environment.getProperty("spring.mail.host");
        Integer smtpPort = environment.getProperty("spring.mail.port", Integer.class, 25);
        String smtpUsername = environment.getProperty("spring.mail.username");
        String smtpPassword = environment.getProperty("spring.mail.password");
        boolean smtpAuth = environment.getProperty("spring.mail.properties.mail.smtp.auth", Boolean.class, false);
        boolean starttlsEnable = environment.getProperty("spring.mail.properties.mail.smtp.starttls.enable", Boolean.class, false);

        if (smtpHost == null || smtpHost.isBlank()) {
            log.warn("Mail configuration missing host; skipping email send.");
            return;
        }
        if (smtpPort == null || smtpPort <= 0) {
            log.warn("Mail configuration has invalid port {}; skipping email send.", smtpPort);
            return;
        }

        if (smtpAuth && (smtpUsername == null || smtpUsername.isBlank() || smtpPassword == null)) {
            log.warn("Mail configuration requires authentication but credentials are missing; skipping email send.");
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
