package com.example.Utpatur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    String skapanytur(Model model) {
        model.addAttribute("route", new Route());
        //dbRepository.testCreateNewRouteObject();
        System.out.println("Vi är på rad 36");
        return "skapa-ny-tur";
    }

    @PostMapping("/skapa-ny-tur")
    String skapaNyTurForm(@ModelAttribute Route route, Model model){

        System.out.println("vi är på rad 43");
        model.addAttribute("route", route);
        //System.out.println("route name: " + route.getRouteName());
        System.out.println("route type: " + route.getRouteType());

        return "index";
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
