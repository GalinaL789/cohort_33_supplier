package de.ait.tr.cohort_33_supplier.service.interfaces;

import de.ait.tr.g_33_shop.domain.dto.ProductSupplyDto;

import java.util.List;

public interface HttpService {

    List<ProductSupplyDto> getProductsToRequest();
}
