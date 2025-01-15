package de.ait.tr.cohort_33_supplier;

import de.ait.tr.g_33_shop.domain.dto.ProductSupplyDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Cohort33SupplierApplication {
//hi111
	public static void main(String[] args) {
		ProductSupplyDto productSupplyDto=new ProductSupplyDto();

		SpringApplication.run(Cohort33SupplierApplication.class, args);
	}

}
