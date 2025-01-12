package de.ait.tr.cohort_33_supplier.scheduler;

import de.ait.tr.cohort_33_supplier.service.interfaces.HttpService;
import de.ait.tr.cohort_33_supplier.service.interfaces.RequestCalculator;
import de.ait.tr.cohort_33_supplier.service.interfaces.SupplyRequestService;
import de.ait.tr.g_33_shop.domain.dto.ProductSupplyDto;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@EnableScheduling
public class SupplyTaskExecutor {

    private final HttpService httpService;
    private final RequestCalculator requestCalculator;
    private final SupplyRequestService supplyRequestService;

    public SupplyTaskExecutor(HttpService httpService, RequestCalculator requestCalculator, SupplyRequestService supplyRequestService) {
        this.httpService = httpService;
        this.requestCalculator = requestCalculator;
        this.supplyRequestService = supplyRequestService;
    }

    @Scheduled(cron = "0,50 * * * * *")
    public void sendSupplyRequest()
    {
        List<ProductSupplyDto> productToRequest = httpService.getProductsToRequest();
        Map<String,Integer> supplyRequest=requestCalculator.calculateRequests(productToRequest);
        supplyRequestService.sendSupplyRequest(supplyRequest);
        //1. по http  обратиться к бекэнду магазина и запросить у него товарные остатки
        // зная, какое количество продуктов мы должны поддерживать в нашем магазине
        // высчитать заказ поставщику, сколько и каких продуктов заказать
        // Рассчитанный
    }
}
