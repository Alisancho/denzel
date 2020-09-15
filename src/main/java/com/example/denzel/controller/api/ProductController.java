package com.example.denzel.controller.api;

import com.example.denzel.entity.elastic.User;
import com.example.denzel.repository.elastic.UserRepository;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@Api(value = "onlinestore", description = "Operations pertaining to products in Online Store")
public class ProductController {

    private String getIDUser() {
        return "9-" + UUID.randomUUID().toString().replace("-", "").substring(0, 5);
    }

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/addUser")
    public String addUser(@RequestParam("lastname") Optional<String> lastname,
                          @RequestParam("firstname") Optional<String> firstname,
                          @RequestParam("age") Optional<Integer> age) {
        if (lastname.isPresent() && firstname.isPresent() && age.isPresent()) {
            log.info("New user");
            userRepository.save(new User(getIDUser(), lastname.get(), firstname.get(), age.get()));
        }
        return "ok";
    }

    @GetMapping("getAllUsers")
    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

}
