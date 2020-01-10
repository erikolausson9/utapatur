package com.example.Utpatur;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MapAPIRestController {


    @GetMapping("/testRoute")
    public TestRouteObject testRoute(){

        List<Double> position = new ArrayList();
        position.add(67.916602);
        position.add(18.583376);

        TestRouteObject testRouteObject = new TestRouteObject("Tarfalasjön", "mountainTop", position );

        return testRouteObject;

    }



}
