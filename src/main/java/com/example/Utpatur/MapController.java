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

/*    @Autowired
    DbRepository dbRepository;*/

    @Autowired
    ServiceLayer serviceLayer;

    @GetMapping("/")
    String startsida() {
        return "index";
    }

    @GetMapping("/skapa-ny-tur")
    String skapanytur(Model model) {
        model.addAttribute("createNewRoute", new CreateNewRoute());
        //dbRepository.testCreateNewRouteObject();
        System.out.println("Vi är på rad 36");
        return "skapa-ny-tur";
    }

    @PostMapping("/skapa-ny-tur")
    String skapaNyTurForm(@ModelAttribute CreateNewRoute createNewRoute, Model model){

        //System.out.println("vi är på rad 43");
        model.addAttribute("createNewRoute", createNewRoute);
        /*
        System.out.println("route name: " + createNewRoute.getRouteName());
        System.out.println("route type: " + createNewRoute.getRouteType());
        System.out.println("route description: " + createNewRoute.getDescription());
        System.out.println("route days: " + createNewRoute.getDays());
        System.out.println("route hours: " + createNewRoute.getHours());
        System.out.println("route date: " + createNewRoute.getDateOfCompletion());
        System.out.println("length: " + createNewRoute.getLength());
        System.out.println("height: " + createNewRoute.getHeight());
        System.out.println("latitudes: " + createNewRoute.getLongitudes());
        System.out.println("longitudes: " + createNewRoute.getLatitudes());
        */
        createNewRoute.setMemberId(1);

        serviceLayer.addRoute(createNewRoute);

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
