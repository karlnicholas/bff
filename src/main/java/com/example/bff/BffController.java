package com.example.bff;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BffController {

    @GetMapping("/")
    public String home(Model model) {
        return setIndexModel(model);
    }

    private String setIndexModel(Model model) {
        model.addAttribute("transferAmount", new TransferAmount());
        return "index";
    }
}
