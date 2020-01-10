package com.example.Utpatur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class UtPaTurController {

    @Autowired
    DbRepository dbRepository;

    @GetMapping("/")
    String startsida() {
        return "index";
    }

    @GetMapping("/skapa-ny-tur")
    String skapanytur() {

        //dbRepository.testCreateNewRouteObject();

            return "skapa-ny-tur";
        }

        @GetMapping("/profil")
        String profil(Model model) {

        return "profile";
        }

}
