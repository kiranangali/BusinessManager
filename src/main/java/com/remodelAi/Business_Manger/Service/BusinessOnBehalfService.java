package com.remodelAi.Business_Manger.Service;
import com.remodelAi.Business_Manger.Model.BusinessOnBehalf;

import com.remodelAi.Business_Manger.Repository.BusinessOnBehalfRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class BusinessOnBehalfService {
  private final BusinessOnBehalfRepository businessOnBehalfRepository;
    private final RestTemplate restTemplate;

    public BusinessOnBehalfService(BusinessOnBehalfRepository businessOnBehalfRepository, RestTemplate restTemplate) {
        this.businessOnBehalfRepository = businessOnBehalfRepository;
        this.restTemplate = restTemplate;
    }
    public void createManagedBusiness(BusinessOnBehalf businessOnBehalf) {
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("existing_client_business_id", businessOnBehalf.getClientBusinessManagerId());
        requestBody.add("access_token", businessOnBehalf.getUsersAccessToken());

        // Make the POST request to the Facebook Graph API
        ResponseEntity<String> response = restTemplate.postForEntity(
                "https://graph.facebook.com/v19.0/{partnerBusinessManagerId}/managed_businesses",
                requestBody,
                String.class,
                businessOnBehalf.getPartnerBusinessManagerId()
        );

        // Check the response status
        if (response.getStatusCode().is2xxSuccessful()) {
            // Save the updated BusinessManager entity
            businessOnBehalfRepository.save(businessOnBehalf);
        } else {
            // Handle the error
            throw new RuntimeException("Failed to create managed business: " + response.getBody());
        }
    }
    public void createAccessToken(BusinessOnBehalf BusinessOnBehalf) {
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("scope", "ads_management,pages_read_engagement");
        requestBody.add("app_id", BusinessOnBehalf.getAppId());
        requestBody.add("access_token", BusinessOnBehalf.getPartnerBusinessManagerAdminSystemUserAccessToken());

        // Make the POST request to the Facebook Graph API
        ResponseEntity<String> response = restTemplate.postForEntity(
                "https://graph.facebook.com/v19.0/{clientBusinessManagerId}/access_token",
                requestBody,
                String.class,
                BusinessOnBehalf.getClientBusinessManagerId()
        );

        // Check the response status
        if (response.getStatusCode().is2xxSuccessful()) {
            // Save the updated BusinessManager entity
            businessOnBehalfRepository.save(BusinessOnBehalf);
        } else {
            // Handle the error
            throw new RuntimeException("Failed to create access token: " + response.getBody());
        }
    }
    public BusinessOnBehalf getMe(BusinessOnBehalf BusinessOnBehalf) {
        // Make the GET request to the Facebook Graph API
        ResponseEntity<BusinessOnBehalf> response = restTemplate.getForEntity(
                "https://graph.facebook.com/v19.0/me?access_token={accessToken}",
                BusinessOnBehalf.class,
                BusinessOnBehalf.getClientBusinessManagerSuperUserAccessToken()
        );

        // Check the response status
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            // Handle the error
            throw new RuntimeException("Failed to get current user: " + response.getBody());
        }
    }

    public void assignUserToAsset(BusinessOnBehalf BusinessOnBehalf) {
        // Prepare the request body
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("user",BusinessOnBehalf.getSystemUserId());
        requestBody.add("tasks", "MANAGE");
        requestBody.add("access_token", BusinessOnBehalf.getUsersAccessToken());

        // Make the POST request to the Facebook Graph API
        ResponseEntity<String> response = restTemplate.postForEntity(
                "https://graph.facebook.com/v19.0/{assetId}/assigned_users",
                requestBody,
                String.class,
                BusinessOnBehalf.getAssetId()
        );

        // Check the response status
        if (response.getStatusCode().is2xxSuccessful()) {
            // Save the updated BusinessManager entity
            businessOnBehalfRepository.save(BusinessOnBehalf);
        } else {
            // Handle the error
            throw new RuntimeException("Failed to assign user to asset: " + response.getBody());
        }
    }

    public List<BusinessOnBehalf> getAccounts(BusinessOnBehalf BusinessOnBehalf) {
        // Make the GET request to the Facebook Graph API
        ResponseEntity<BusinessOnBehalf[]> response = restTemplate.getForEntity(
                "https://graph.facebook.com/v19.0/me/accounts?access_token={accessToken}",
                BusinessOnBehalf[].class,
                BusinessOnBehalf.getClientBusinessManagerSuperUserAccessToken()
        );

        // Check the response status
        if (response.getStatusCode().is2xxSuccessful()) {
            return Arrays.asList(response.getBody());
        } else {
            // Handle the error
            throw new RuntimeException("Failed to get accounts: " + response.getBody());
        }
    }

    public void deleteManagedBusiness(BusinessOnBehalf BusinessOnBehalf) {
        // Prepare the request body
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("existing_client_business_id",BusinessOnBehalf.getClientBusinessManagerId());
        requestBody.add("access_token", BusinessOnBehalf.getUsersAccessToken());

        // Make the DELETE request to the Facebook Graph API
        ResponseEntity<String> response = restTemplate.exchange(
                "https://graph.facebook.com/v19.0/{partnerBusinessManagerId}/managed_businesses",
                HttpMethod.DELETE,
                new HttpEntity<>(requestBody),
                String.class,
                BusinessOnBehalf.getPartnerBusinessManagerId()
        );

        // Check the response status
        if (response.getStatusCode().is2xxSuccessful()) {
            // Remove the managed business from the repository
            businessOnBehalfRepository.delete(BusinessOnBehalf);
        } else {
            // Handle the error
            throw new RuntimeException("Failed to delete managed business: " + response.getBody());
        }
    }
}
