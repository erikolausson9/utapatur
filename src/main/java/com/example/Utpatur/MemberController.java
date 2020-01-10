package com.example.Utpatur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @Autowired
    DbRepository dbRepository;

    @GetMapping("/profil")
    String profil(Model model) {

        return "profile";
    }
}
