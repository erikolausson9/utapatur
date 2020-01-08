package com.example.Utpatur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UtPaTurController {

    @GetMapping("/")
    String startsida(){
        return "index";
    }

    @GetMapping("/skapa-ny-tur")
        String skapanytur() {
            return "skapa-ny-tur";
        }


}
