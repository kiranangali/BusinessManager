package com.remodelAi.Business_Manger.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BusinessOnBehalf {
    @Id
    private Long id;
    private String partnerBusinessManagerId;
    private String clientBusinessManagerId;
    private String usersAccessToken;
    private String partnerBusinessManagerAdminSystemUserAccessToken;
    private String clientBusinessManagerSuperUserAccessToken;
    private String appId;
    private String assetId;
    private String systemUserId;
}
