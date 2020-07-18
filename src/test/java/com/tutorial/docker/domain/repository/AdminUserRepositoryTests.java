package com.tutorial.docker.domain.repository;

import com.tutorial.docker.DockerApplicationTests;
import com.tutorial.docker.domain.model.AdminUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AdminUserRepositoryTests extends DockerApplicationTests {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Test
    public void create() {
        AdminUser adminUser = AdminUser.builder()
                .email("admin01@example.com")
                .name("admin01")
                .password("admin01")
                .status("REGISTERED")
                .role("ADMIN")
                .createdAt(LocalDateTime.now())
                .createdBy("ADMIN")
                .build();

        AdminUser saved = adminUserRepository.save(adminUser);

        assertNotNull(saved);
        assertEquals(saved.getEmail(), adminUser.getEmail());
    }
}