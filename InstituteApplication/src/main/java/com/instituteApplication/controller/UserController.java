package com.instituteApplication.controller;

import com.instituteApplication.model.User;
import com.instituteApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //With this api we can user as faculty or admin
    //@PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String addUser(@RequestBody User user){
        userService.addUser(user);
        return "User saved successfully";
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('FACULTY')")
    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public String resetPassword(@PathVariable long userId, @RequestParam String password){
        userService.resetPassword(userId, password);
        return "Password Updated Successfully";
    }
}
