package com.tutorial.docker.service;

import com.tutorial.docker.domain.request.AdminUserCreateRequest;
import com.tutorial.docker.domain.request.AdminUserUpdateRequest;
import com.tutorial.docker.domain.response.AdminUserResponse;
import com.tutorial.docker.domain.model.AdminUser;
import com.tutorial.docker.domain.repository.AdminUserRepository;
import com.tutorial.docker.exception.UserDuplicationException;
import com.tutorial.docker.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdminUserService {

    private final AdminUserRepository adminUserRepository;

    @Autowired
    public AdminUserService(AdminUserRepository adminUserRepository) {
        this.adminUserRepository = adminUserRepository;
    }

    public List<AdminUserResponse> list() {
        List<AdminUser> adminUsers = adminUserRepository.findAll();
        return response(adminUsers);
    }

    public AdminUserResponse detail(Long id) {
        AdminUser adminUser = adminUserRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return response(adminUser);
    }

    public AdminUserResponse create(AdminUserCreateRequest request) {
        if(adminUserRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserDuplicationException(request.getEmail());
        }

        AdminUser adminUser = AdminUser.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password("abc")
                .status("REGISTERED")
                .role("ADMIN")
                .createdAt(LocalDateTime.now())
                .createdBy("ADMIN")
                .build();

        AdminUser saved = adminUserRepository.save(adminUser);

        return response(saved);
    }

    public AdminUserResponse update(AdminUserUpdateRequest request, Long id) {
        AdminUser adminUser = adminUserRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        adminUser.update(request);

        return response(adminUser);
    }

    public void delete(Long id) {
        if(!adminUserRepository.findById(id).isPresent()) {
            throw new UserNotFoundException(id);
        }
        adminUserRepository.deleteById(id);
    }

    private List<AdminUserResponse> response(List<AdminUser> adminUsers) {
        List<AdminUserResponse> data = adminUsers.stream()
                .map(adminUser -> AdminUserResponse.builder()
                        .id(adminUser.getId())
                        .email(adminUser.getEmail())
                        .name(adminUser.getName())
                        .status(adminUser.getStatus())
                        .role(adminUser.getRole())
                        .createdAt(adminUser.getCreatedAt())
                        .createdBy(adminUser.getCreatedBy())
                        .updatedAt(adminUser.getUpdatedAt())
                        .updatedBy(adminUser.getUpdatedBy())
                        .build())
                .collect(Collectors.toList());

        return data;
    }

    private AdminUserResponse response(AdminUser adminUser) {
        AdminUserResponse data = AdminUserResponse.builder()
                .id(adminUser.getId())
                .email(adminUser.getEmail())
                .name(adminUser.getName())
                .status(adminUser.getStatus())
                .role(adminUser.getRole())
                .createdAt(adminUser.getCreatedAt())
                .createdBy(adminUser.getCreatedBy())
                .updatedAt(adminUser.getUpdatedAt())
                .updatedBy(adminUser.getUpdatedBy())
                .build();

        return data;
    }
}
