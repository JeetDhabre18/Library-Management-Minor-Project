package org.gfg.Library_Management_Minor_Project.controller;

import org.gfg.Library_Management_Minor_Project.dto.UserRequest;
import org.gfg.Library_Management_Minor_Project.model.User;
import org.gfg.Library_Management_Minor_Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addStudent")
    public User addStudent(@RequestBody UserRequest userRequest) {
         return userService.addStudent(userRequest);
    }

    @PostMapping("/addAdmin")
    public User addAdmin(@RequestBody UserRequest userRequest) {
         return null;
    }

    @GetMapping("/filter")
    public List<User> filter(@RequestParam("filterBy") String filterBy,
                             @RequestParam("operator") String operator,
                             @RequestParam ("value") String value) {
        return userService.filter(filterBy,operator,value);
   }
}
