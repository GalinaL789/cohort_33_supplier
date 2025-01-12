package de.ait.tr.cohort_33_supplier.service;

import de.ait.tr.cohort_33_supplier.service.interfaces.EmailService;
import de.ait.tr.cohort_33_supplier.service.interfaces.SupplyRequestService;
import de.ait.tr.g_33_shop.domain.entity.User;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class SupplyRequestServiceImpl implements SupplyRequestService {
    private final EmailService emailService;

    public SupplyRequestServiceImpl(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void sendSupplyRequest(Map<String, Integer> supplyRequest) {
        supplyRequest.forEach((key, value) -> System.out.println(key + " = " + value));
        // set Email to supplier
        User user = new User();
        user.setUsername("Andlus");
        user.setEmail("onthemountain89@gmail.com");
        emailService.sendSupplierEmail(user, supplyRequest);
    }
}
