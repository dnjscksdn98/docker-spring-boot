package com.tutorial.docker.controller;

import com.tutorial.docker.domain.request.AdminUserCreateRequest;
import com.tutorial.docker.domain.request.AdminUserUpdateRequest;
import com.tutorial.docker.domain.response.AdminUserResponse;
import com.tutorial.docker.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "users")
public class AdminUserApiController {

    @Autowired
    private AdminUserService adminUserService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AdminUserResponse>> list() {
        List<AdminUserResponse> body = adminUserService.list();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminUserResponse> detail(@PathVariable("id") Long id) {
        AdminUserResponse body = adminUserService.detail(id);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminUserResponse> create(@Valid @RequestBody AdminUserCreateRequest request) {
        AdminUserResponse body = adminUserService.create(request);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminUserResponse> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody AdminUserUpdateRequest request) {
        AdminUserResponse body = adminUserService.update(request, id);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        adminUserService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
