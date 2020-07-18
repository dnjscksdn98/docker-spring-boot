package com.tutorial.docker.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserUpdateRequest {

    @Email
    private String email;

    private String name;

    private String password;

    private String status;

}
