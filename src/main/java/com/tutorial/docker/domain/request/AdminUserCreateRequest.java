package com.tutorial.docker.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserCreateRequest {

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String name;

}
