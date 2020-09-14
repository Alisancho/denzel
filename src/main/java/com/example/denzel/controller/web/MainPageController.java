package com.example.denzel.controller.web;

import com.example.denzel.entity.elastic.User;
import com.example.denzel.repository.elastic.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/main")
public class MainPageController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String mainPage(final Model model) throws ExecutionException, InterruptedException {
        final var user = new User("9-asegsrg","Alex","Kook",34);
        userRepository.save(user);
        return "untitled";
    }
}
