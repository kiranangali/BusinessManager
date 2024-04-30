package com.remodelAi.Business_Manger.Model;

import com.remodelAi.Business_Manger.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class BusinessManagerDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String vertical;
    private String primaryPage;
    private int timezoneId;
    @Size(max = 255)
    @Email(message = "Invalid email address")
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Value("${facebook.access.token}")
    private String accessToken;

//    public BusinessManagerDTO(String accessToken) {
//
//    }
//    public Role getRole() {
//        return role;
//    }
//
//    public Role setRole(Role role) {
//        this.role = role;
//    }
//
//    public String getAccessToken() {
//        return accessToken;
//    }
//
//    public void setAccessToken(String accessToken) {
//        this.accessToken = accessToken;
//    }
//
//    public BusinessManagerDTO(Long id, String name, String vertical, String primaryPage, int timezoneId,String accessToken,Role role,String email) {
//        this.id = id;
//        this.name = name;
//        this.vertical = vertical;
//        this.primaryPage = primaryPage;
//        this.timezoneId = timezoneId;
//        this.accessToken = accessToken;
//        this.role =role;
//        this.email = email;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getVertical() {
//        return vertical;
//    }
//
//    public void setVertical(String vertical) {
//        this.vertical = vertical;
//    }
//
//    public String getPrimaryPage() {
//        return primaryPage;
//    }
//
//    public void setPrimaryPage(String primaryPage) {
//        this.primaryPage = primaryPage;
//    }
//
//    public int getTimezoneId() {
//        return timezoneId;
//    }
//
//    public void setTimezoneId(int timezoneId) {
//        this.timezoneId = timezoneId;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
}

