package com.example.Utpatur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MapAPIRestController {


    @Autowired
    ServiceLayer serviceLayer;

    @GetMapping("/getallfromdb")
    public List<Route> getAllFromDb(){

        List<Route> routes = serviceLayer.getAllforMap();

        return routes;
    }


}
