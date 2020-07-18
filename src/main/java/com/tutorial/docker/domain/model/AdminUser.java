package com.tutorial.docker.domain.model;

import com.tutorial.docker.domain.request.AdminUserUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    private String password;

    private String status;

    private String role;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    public void update(AdminUserUpdateRequest request){
        String email = request.getEmail();
        String name = request.getName();
        String password = request.getPassword();
        String status = request.getStatus();

        if(email != null) this.email = email;
        if(name != null) this.name = name;
        if(password != null) this.password = password;
        if(status != null) this.status = status;
    }
}
