package com.google.training.appdev;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class AnswerController {

    @GetMapping("answer")
    public String getAccounts(@RequestParam("uid") String uid) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return "{\"uid\": \"" + uid + "\", \"principal\": \"" + name + "\"}";
    }

}
