package com.example.Utpatur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UtPaTurController {

    @GetMapping("/index")
    String startsida(){
        return "index";
    }

}
