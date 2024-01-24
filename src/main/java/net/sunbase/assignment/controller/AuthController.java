package net.sunbase.assignment.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "http://localhost:63342") // Allow requests from your frontend origin
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String url = "https://qa.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";

        // Set up the headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Set up the request entity with body and headers
        HttpEntity<LoginRequest> requestEntity = new HttpEntity<>(loginRequest, headers);

        // Create a RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Send POST request and retrieve the response
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);

        // You can customize the response based on your requirements
        return responseEntity;
    }

    // Define a simple POJO to represent the login request
    static class LoginRequest {
        private String login_id;
        private String password;

        // getters and setters

        public String getLogin_id() {
            return login_id;
        }

        public void setLogin_id(String login_id) {
            this.login_id = login_id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
