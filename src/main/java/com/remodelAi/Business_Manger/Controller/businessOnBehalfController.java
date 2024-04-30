package com.remodelAi.Business_Manger.Controller;

import com.remodelAi.Business_Manger.Model.BusinessOnBehalf;
import com.remodelAi.Business_Manger.Service.BusinessOnBehalfService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/business-managers")
public class businessOnBehalfController {
    private final BusinessOnBehalfService service;

    public  businessOnBehalfController(BusinessOnBehalfService service) {
        this.service = service;
    }

    @PostMapping("/managed-businesses")
    public ResponseEntity<Void> createManagedBusiness(@RequestBody BusinessOnBehalf businessManager) {
        service.createManagedBusiness(businessManager);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/access-tokens")
    public ResponseEntity<Void> createAccessToken(@RequestBody BusinessOnBehalf businessManager) {
        service.createAccessToken(businessManager);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/me")
    public ResponseEntity<BusinessOnBehalf> getMe(@RequestBody BusinessOnBehalf businessManager) {
        BusinessOnBehalf me = service.getMe(businessManager);
        return ResponseEntity.ok(me);
    }

    @PostMapping("/assets/{assetId}/users")
    public ResponseEntity<Void> assignUserToAsset(@PathVariable String assetId, @RequestBody BusinessOnBehalf businessManager) {
        businessManager.setAssetId(assetId);
        service.assignUserToAsset(businessManager);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<BusinessOnBehalf>> getAccounts(@RequestBody BusinessOnBehalf businessManager) {
        List<BusinessOnBehalf> accounts = service.getAccounts(businessManager);
        return ResponseEntity.ok(accounts);
    }

    @DeleteMapping("/managed-businesses")
    public ResponseEntity<Void> deleteManagedBusiness(@RequestBody BusinessOnBehalf businessManager) {
        service.deleteManagedBusiness(businessManager);
        return ResponseEntity.noContent().build();
    }
}