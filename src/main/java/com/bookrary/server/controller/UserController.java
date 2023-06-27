package com.bookrary.server.controller;

import com.bookrary.server.model.response.UserResponse;
import com.bookrary.server.model.response.UserStatsResponse;
import com.bookrary.server.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/{userId}")
    public UserResponse getUser(@PathVariable String userId) {
        return userService.getUser(userId);
    }

    @GetMapping("/stats/{userId}")
    public UserStatsResponse getStats(@PathVariable String userId) {
        return userService.getUserStats(userId);
    }
}
