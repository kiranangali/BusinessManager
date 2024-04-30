package com.remodelAi.Business_Manger.Service;

import com.remodelAi.Business_Manger.Model.BusinessManagerDTO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class BusinessManagerService {
    @Value("${facebook.access.token}")
    private String accessToken;

    @Value("${facebook.api.version}")
    private String apiVersion;

    @Value("${facebook.user.id}")
    private String userId;

    @Value("${facebook.business.create.url}")
    private String createBusinessUrl;
    @Value("${facebook.business.id}")
    private String businessId;
    private final RestTemplate restTemplate;

    public BusinessManagerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String createBusinessManager(BusinessManagerDTO request) {
        // Prepare the URL
        String url = createBusinessUrl.replace("${facebook.api.version}", apiVersion)
                .replace("${facebook.user.id}", userId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("name", request.getName());
        body.add("vertical", request.getVertical());
        body.add("primary_page", request.getPrimaryPage());
        body.add("timezone_id", request.getTimezoneId());
        body.add("access_token", accessToken); // Include the access token in the body

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return "Business Manager created successfully!";
        } else {
            return "Failed to create Business Manager. Response: " + response.getBody();
        }
    }

    public String updateBusinessManager(BusinessManagerDTO request) {
        // Prepare the URL
        String url = "https://graph.facebook.com/" + apiVersion + "/" + businessId;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("name", request.getName());
        body.add("access_token", accessToken); // Include the access token in the body

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return "Business Manager updated successfully!";
        } else {
            return "Failed to update Business Manager. Response: " + response.getBody();
        }
    }

    public String updateBusinessVertical(BusinessManagerDTO request) {

        String url = "https://graph.facebook.com/" + apiVersion + "/" + businessId;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("vertical", request.getVertical());
        body.add("access_token", accessToken);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return "Business Manager updated Vertical successfully!";
        } else {
            return "Failed to update Business Manager. Response: " + response.getBody();
        }
    }

    public String updatePrimaryPage(BusinessManagerDTO request) {

        String url = "https://graph.facebook.com/" + apiVersion + "/" + businessId;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("primarypage", request.getPrimaryPage());
        body.add("access_token", accessToken);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return "Business Manager updated Vertical successfully!";
        } else {
            return "Failed to update Business Manager. Response: " + response.getBody();
        }

    }

    public String updateAll(BusinessManagerDTO request) {

        String url = "https://graph.facebook.com/" + apiVersion + "/" + businessId;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("name", request.getName());
        body.add("vertical", request.getVertical());
        body.add("primarypage", request.getPrimaryPage());
        body.add("access_token", accessToken);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return "Business Manager updated Vertical successfully!";
        } else {
            return "Failed to update Business Manager. Response: " + response.getBody();
        }


    }

    public String invitePeople(BusinessManagerDTO request) {

        String url = "https://graph.facebook.com/" + apiVersion + "/" + businessId + "/business_users";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("email", request.getEmail());
        body.add("role", request.getRole().getRole());
        body.add("access_token", accessToken);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return "Business Manager invitation sent successfully!";
        } else {
            return "Failed to update Business Manager. Response: " + response.getBody();
        }
    }

    public String getbusinessUsers() {
        String url = "https://graph.facebook.com/" + apiVersion + "/" + businessId + "/" + "business_users?access_token=" + accessToken;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }

    public String getsystemUsers() {
        String url = "https://graph.facebook.com/" + apiVersion + "/" + businessId + "/" + "system_users?access_token=" + accessToken;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }
    public String getpendingusersinvited() {
        String url = "https://graph.facebook.com/" + apiVersion + "/" + businessId + "/" + "pending_users?access_token=" + accessToken;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }
    public String getToremoveanactiveuser(BusinessManagerDTO request, String businessScopedUserID ) {
        String url = "https://graph.facebook.com/" + apiVersion + "/" + businessScopedUserID;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("access_token", accessToken);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return "Business Manager invitation sent successfully!";
        } else {
            return "Failed to update Business Manager. Response: " + response.getBody();
        }
    }
    public String getTocancelapendinguser (BusinessManagerDTO request, String PendingUsedId) {
        String url = "https://graph.facebook.com/" + apiVersion + "/" + PendingUsedId;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("access_token", accessToken);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return "Business Manager invitation sent successfully!";
        } else {
            return "Failed to update Business Manager. Response: " + response.getBody();
        }
    }
//    public String Toretrieveinvoices(){
//
//    }
//public String getinvoicedetails_at_a_campaignleve(){
//
//}
}