package de.ait.tr.cohort_33_supplier.service;

import de.ait.tr.cohort_33_supplier.service.interfaces.RequestCalculator;
import de.ait.tr.g_33_shop.domain.dto.ProductSupplyDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RequestCalculatorImpl implements RequestCalculator {

    private final Map<String, Integer> requiredQuantities = Map.of(
            "banana", 15,
            "orange", 20,
            "apple", 17,
            "peach", 22,
            "coconut", 12,
            "pineapple", 14,
            "milk", 18
    );

    @Override
    public Map<String, Integer> calculateRequests(List<ProductSupplyDto> products) {

        Map<String, Integer> differenceMap = new HashMap<>();
        for (ProductSupplyDto product : products) {

            String title = product.getTitle();
            int availableQuantity = product.getQuantity();
            if (requiredQuantities.containsKey(title)) {
                int requiredQuantity = requiredQuantities.get(title);
                int differenceQuantity = availableQuantity - requiredQuantity;

                if (differenceQuantity <= 0) {
                    // add to Map
                    differenceMap.put(title, differenceQuantity);
                }
                System.out.println("product Title: " + product.getTitle());
                System.out.println("product availableQuantity: " + product.getQuantity());
                System.out.println("difference: " + differenceQuantity);
                System.out.println("--- ------");
            }
        }
        return differenceMap;
    }
}
