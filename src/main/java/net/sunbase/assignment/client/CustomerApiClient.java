package net.sunbase.assignment.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sunbase.assignment.entity.Customer;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class CustomerApiClient {
    private final String apiBaseUrl = "https://qa.sunbasedata.com/sunbase/portal/api/assignment.jsp";
    private final String token = "dGVzdEBzdW5iYXNlZGF0YS5jb206VGVzdEAxMjM=";
    public List<Customer> getCustomerList() {
        String url = apiBaseUrl + "?cmd=get_customer_list";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                // Parse the JSON response
                ObjectMapper objectMapper = new ObjectMapper();
                List<Customer> customers = objectMapper.readValue(response.getBody(), new TypeReference<List<Customer>>() {});
                return customers;
            } else {
                System.err.println("Error: " + response.getStatusCodeValue() + ", " + response.getBody());
                return null;
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            return null;
        }
    }
}
