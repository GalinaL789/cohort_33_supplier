package de.ait.tr.cohort_33_supplier.service.interfaces;

import de.ait.tr.g_33_shop.domain.dto.ProductDto;
import de.ait.tr.g_33_shop.domain.dto.ProductSupplyDto;

import java.util.List;
import java.util.Map;


public interface RequestCalculator {
    Map<String, Integer> calculateRequests(List<ProductSupplyDto> products);
}
