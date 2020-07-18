package com.tutorial.docker.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserResponse {

    private Long id;

    private String email;

    private String name;

    private String status;

    private String role;

    private LocalDateTime createdAt;

    private String createdBy;

    @JsonInclude(Include.NON_NULL)
    private LocalDateTime updatedAt;

    @JsonInclude(Include.NON_NULL)
    private String updatedBy;
}
