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

    @GetMapping("/testgetallfromdb")
    public List<Route> getAllFromDb(){

        return serviceLayer.getAllRoutes();
    }

    @GetMapping("/testgetroutefromdb/{id}")
    public Route getRouteFromDb(@PathVariable int id){

        return serviceLayer.getRoute(id);
    }

    @GetMapping("/testgetpositionfromdb/{id}")
    public List<Position> getPositionsFromDb(@PathVariable int id){
        return serviceLayer.getPositions(id);
    }

    @GetMapping("/testgetrouteandpositionfromdb/{id}")
    public Route getRouteAndPositionFromDb(@PathVariable int id){

        Route route = serviceLayer.mergeRouteAndPosition(id);
        return route;
    }


//    @GetMapping("/testgetobject")
//    public TestRouteObject testRoute(){
//
//        List<Double> position = new ArrayList();
//        position.add(67.916602);
//        position.add(18.583376);
//
//        //TestRouteObject testRouteObject = new TestRouteObject("Tarfalasjön", "poi", position );
//
//        return testRouteObject;
//
//    }

    @GetMapping("/testgetallroutes")
    public List<TestRouteObject> testGetAllRoutes(){

        //Test object 1

        List<TestPosition> position1 = new ArrayList();
        position1.add(new TestPosition(67.916602, 18.583376));

        TestRouteObject testRouteObject1 = new TestRouteObject("Tarfalasjön", "poi", position1 );

        //Test object 2
        List<TestPosition> position2 = new ArrayList();
        position2.add(new TestPosition(67.918486, 18.601353));
        position2.add(new TestPosition(67.903964, 18.621239));
        position2.add(new TestPosition(67.893922, 18.646379));
        position2.add(new TestPosition(67.870871, 18.648868));
        position2.add(new TestPosition(67.868984, 18.617324));


        TestRouteObject testRouteObject2 = new TestRouteObject("Tarfala-Kebnekaise", "hiking", position2 );

        //Test object 3
        List<TestPosition> position3 = new ArrayList();
        position3.add(new TestPosition(67.904363, 18.527085));

        TestRouteObject testRouteObject3 = new TestRouteObject("Kebnekaises sydtopp", "mountaintop", position3);

        List<TestRouteObject> testListOfRouteObjects = new ArrayList<>();

        testListOfRouteObjects.add(testRouteObject1);
        testListOfRouteObjects.add(testRouteObject2);
        testListOfRouteObjects.add(testRouteObject3);

        return testListOfRouteObjects;

    }



}
