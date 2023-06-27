package com.bookrary.server.controller;

import com.bookrary.server.model.request.UpdateUserRequest;
import com.bookrary.server.model.response.UserResponse;
import com.bookrary.server.model.response.UserStatsResponse;
import com.bookrary.server.service.AuthenticationService;
import com.bookrary.server.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private AuthenticationService authenticationService;

    @GetMapping
    public Page<UserResponse> getUsers(@ApiIgnore Pageable pageable) {
        return userService.getUsers(pageable);
    }

    @GetMapping("/{userId}")
    public UserResponse getUser(@PathVariable String userId) {
        return userService.getUser(userId);
    }

    @GetMapping("/stats/{userId}")
    public UserStatsResponse getStats(@PathVariable String userId) {
        return userService.getUserStats(userId);
    }

    @PutMapping("/{userId}")
    public UserResponse updateUser(@PathVariable String userId, @RequestBody UpdateUserRequest updateUserRequest) {
        return userService.updateUser(userId, updateUserRequest, authenticationService.getAuthenticatedUser());
    }
}
