package com.example.Utpatur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MapController {

    @Autowired
    DbRepository dbRepository;

    @Autowired
    //ServiceLayer serviceLayer;

    @GetMapping("/")
    String startsida() {
        return "index";
    }

    @GetMapping("/skapa-ny-tur")
    String skapanytur() {

        //dbRepository.testCreateNewRouteObject();

        return "skapa-ny-tur";
    }







    @GetMapping("/kartvy")
    String kartvy(){
        return "kartvy";
    }


    //tillfälligt, detta behöver sen ändras med routeID och pathvariable
    @GetMapping("tur")
    String tur() {
        return "tur";
    }


}
