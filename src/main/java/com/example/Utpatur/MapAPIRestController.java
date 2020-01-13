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
    public List<Route> testGetAllRoutes(){

        List<Route> routes = new ArrayList<>();

        List<Position> positions = new ArrayList<>();
        positions.add(new Position(67.918486, 18.601353, null, 20));
        positions.add(new Position(67.903964, 18.621239, null, 20));
        positions.add(new Position(67.893922, 18.646379, null, 20));
        positions.add(new Position(67.870871, 18.648868, null, 20));
        positions.add(new Position(67.868984, 18.617324, null, 20));

        Route route = new Route(20, "Tarfala-Kebnekaise", "hiking", null, "Mellan", 22132, null, 8.5, "Fin och enkel tur", "2020-01-08", null, null, 7);
        route.setPositions(positions);


        List<Position> positions2 = new ArrayList<>();
        positions2.add(new Position(67.916602, 18.583376, null, 21));

        Route route2 = new Route(21, "Kebnekaises sydtopp", "mountaintop", 2034, "Svår", 8000, 0, 12.0, "Fantastisk utsikt från Sveriges högsta berg!", "2020-01-09", null, null, 7);
        route2.setPositions(positions2);

        routes.add(route);
        routes.add(route2);


        return routes;

      /*  //Test object 1

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
        testListOfRouteObjects.add(testRouteObject3);*/

        //return testListOfRouteObjects;

    }



}
