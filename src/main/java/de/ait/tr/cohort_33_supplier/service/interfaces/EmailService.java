package de.ait.tr.cohort_33_supplier.service.interfaces;

import de.ait.tr.g_33_shop.domain.entity.User;

import java.util.Map;

public interface EmailService {
    void sendSupplierEmail(User user, Map<String, Integer> supplyRequest);
}
