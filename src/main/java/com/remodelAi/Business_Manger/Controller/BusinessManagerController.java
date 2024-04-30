package com.remodelAi.Business_Manger.Controller;

import com.remodelAi.Business_Manger.Service.BusinessManagerService;
import com.remodelAi.Business_Manger.Model.BusinessManagerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/business-managers")
public class BusinessManagerController {
    private final BusinessManagerService businessManagerService;

    public BusinessManagerController(BusinessManagerService businessManagerService) {
        this.businessManagerService = businessManagerService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createBusinessManager(@RequestBody BusinessManagerDTO request) {
        String response = businessManagerService.createBusinessManager(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/update/{businessId}")
    public ResponseEntity<String> updateBusinessManager( @RequestBody BusinessManagerDTO request) {
        String response = businessManagerService.updateBusinessManager( request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/vertical/update/{businessId}")
    public ResponseEntity<String>updateBusinessVertical( @RequestBody BusinessManagerDTO request){
        String response = businessManagerService.updateBusinessVertical(request);
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PostMapping("/primarypage/update/{businessId}")
    public ResponseEntity<String>updatePrimaryPage( @RequestBody BusinessManagerDTO request){
        String response = businessManagerService.updatePrimaryPage(request);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PostMapping("/updateAll/{businessId}")
    public ResponseEntity<String>updateAll( @RequestBody BusinessManagerDTO request){
        String response = businessManagerService.updateAll(request);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PostMapping("/invite/business_users")
    public ResponseEntity<String>invitePeople(@RequestBody BusinessManagerDTO businessManagerDTO
                                              ){
        String response = businessManagerService.invitePeople(businessManagerDTO);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/business-users")
    public ResponseEntity<String>getbusinessUsers(){
        String systemUsers = businessManagerService.getbusinessUsers();
        return new ResponseEntity<>(systemUsers,HttpStatus.OK);
    }
    @GetMapping("/system-users")
    public ResponseEntity<String>getsystemUsers(){
        String systemUsers = businessManagerService.getsystemUsers();
        return new ResponseEntity<>(systemUsers,HttpStatus.OK);
    }
    @GetMapping("/pendingusers")
    public ResponseEntity<String>getpendingusersinvited(){
        String systemUsers = businessManagerService.getpendingusersinvited();
        return new ResponseEntity<>(systemUsers,HttpStatus.OK);
    }
    @DeleteMapping("/removeanactiveuser")
    public ResponseEntity<String>getToremoveanactiveuser(@RequestBody BusinessManagerDTO request,
                                                         @PathVariable String businessScopedUserID ){
        String systemUsers = businessManagerService.getToremoveanactiveuser(request,businessScopedUserID);
        return new ResponseEntity<>(systemUsers,HttpStatus.OK);
    }
    @DeleteMapping("/Tocancelapendinguser")
    public ResponseEntity<String>getTocancelapendinguser(@RequestBody BusinessManagerDTO request,
                                                         @PathVariable String PendingUsedId ){
        String systemUsers = businessManagerService.getTocancelapendinguser(request,PendingUsedId);
        return new ResponseEntity<>(systemUsers,HttpStatus.OK);
    }

}
