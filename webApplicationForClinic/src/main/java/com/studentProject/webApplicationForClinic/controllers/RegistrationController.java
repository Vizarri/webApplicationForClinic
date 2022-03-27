package com.studentProject.webApplicationForClinic.controllers;

import com.studentProject.webApplicationForClinic.models.Role;
import com.studentProject.webApplicationForClinic.models.Status;
import com.studentProject.webApplicationForClinic.models.User;
import com.studentProject.webApplicationForClinic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {


    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public RegistrationController(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.passwordEncoder= passwordEncoder;
        this.userRepository = userRepository;
    }

    @GetMapping()
    public String registration() {
        return "/students/registration";
    }

    @PostMapping
    public String registration(User user, ModelMap modelMap) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            modelMap.addAttribute("message", "User exist");
            return "/students/registration";
        }
        user.setStatus(Status.ACTIVE);
        user.setRole(Role.ADMIN);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/auth/login";
    }
}
