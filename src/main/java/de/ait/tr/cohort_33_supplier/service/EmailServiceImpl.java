package de.ait.tr.cohort_33_supplier.service;

import de.ait.tr.cohort_33_supplier.service.interfaces.EmailService;
import de.ait.tr.g_33_shop.domain.entity.User;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.collections4.IteratorUtils.forEach;

@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender sender;
    private final Configuration mailConfig;
    private final Map<String, Integer> supplyRequest;


    public EmailServiceImpl(JavaMailSender sender, Configuration mailConfig, Map<String, Integer> supplyRequest) {
        this.sender = sender;
        this.mailConfig = mailConfig;
        this.supplyRequest = supplyRequest;
        mailConfig.setDefaultEncoding("UTF-8");
        mailConfig.setTemplateLoader(new ClassTemplateLoader(de.ait.tr.g_33_shop.service.EmailServiceImpl.class, "/mail/"));
    }

    @Override
    public void sendSupplierEmail(User user, Map<String, Integer> supplyRequest) {


        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        String emailText="Hello Suppliers " +user.getUsername();
        try {
            helper.setFrom("onthemountain89@gmail.com");
            helper.setTo(user.getEmail());
            helper.setSubject("Requirement of supplier");
            helper.setText(emailText, false);

            sender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

//    private String generateEmailText(User user) {
//        try {
//            Template template = mailConfig.getTemplate("supply_request_mail.ftlh");
//            Map<String, Object> templateMap = new HashMap<>();
//            //templateMap.put("missing_products", supplyRequest);
//            //TODO  из supplyRequest сделать таблицу
//            String table="MyTable<br>";
//
//            table=table+"Banana-15";
//            templateMap.put("table", table);
//
//
//            return FreeMarkerTemplateUtils.processTemplateIntoString(template, templateMap);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
private String generateEmailText(User user, Map<String, Integer> supplyRequest) {
    try {
        Template template = mailConfig.getTemplate("supply_request_mail.ftlh");
        Map<String, Object> templateMap = new HashMap<>();

        // Создание строки с информацией о недостающих продуктах
        StringBuilder tableBuilder = new StringBuilder();
        tableBuilder.append("Недостающие продукты:\n");

        for (Map.Entry<String, Integer> entry : supplyRequest.entrySet()) {
            tableBuilder.append(entry.getKey())
                    .append(" - ")
                    .append(entry.getValue())
                    .append("\n");
        }

        String table = tableBuilder.toString();
        templateMap.put("table", table);

        return FreeMarkerTemplateUtils.processTemplateIntoString(template, templateMap);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}

}

