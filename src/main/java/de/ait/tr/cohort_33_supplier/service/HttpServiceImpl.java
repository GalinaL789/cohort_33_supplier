package de.ait.tr.cohort_33_supplier.service;

import de.ait.tr.cohort_33_supplier.service.interfaces.HttpService;
import de.ait.tr.g_33_shop.domain.dto.ProductSupplyDto;
import de.ait.tr.g_33_shop.domain.entity.User;
import de.ait.tr.g_33_shop.security.sec_dto.TokenResponseDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
@Service
public class HttpServiceImpl implements HttpService {

    private final RestTemplate template=new RestTemplate();
    @Override
    public List<ProductSupplyDto> getProductsToRequest() {
      String token=login();
      HttpHeaders headers =new HttpHeaders();
      headers.put("Authorization",List.of(token));
      HttpEntity<Void> request =new HttpEntity<>(headers);
      String url="http://localhost:8080/products/all";
      HttpEntity<ProductSupplyDto[]> response =template
              .exchange(url, HttpMethod.GET,request,ProductSupplyDto[].class);
        if(!response.hasBody())
        {
            throw new RuntimeException("All products  response has no body");
        }
        return Arrays.asList(response.getBody());
    }

    private String login()
    {
       User user=new User();
       user.setUsername("Andrej");
       user.setPassword("12345");
       HttpHeaders headers=new HttpHeaders();
       HttpEntity<User> request=new HttpEntity<> (user,headers);
       String url="http://localhost:8080/auth/login";
       ResponseEntity<TokenResponseDto> response=template.postForEntity(url, request, TokenResponseDto.class);
       if(!response.hasBody())
       {
           throw new RuntimeException("Auth response has no body");
       }
       return "Bearer " + response.getBody().getAccessToken();
    }
}
