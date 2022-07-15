package com.roe.almaserver.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping(path = "/current", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCurrent() {
        return "current";
    }

    @GetMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getUser(@PathVariable  Long userId) {
        return "userId";
    }

}
