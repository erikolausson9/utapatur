package com.example.Utpatur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceLayer {
    @Autowired
    DbRepository dbRepository;

    @Autowired
    MemberRepository memberRepository;

    //instance variable used for storing the route we want to see in detail view
    private Route routeToShow;

    public Route getRouteToShow(){
        System.out.println("in getRouteToShow i servicelagret. routeToShow: " + routeToShow);
        return routeToShow;}


    //instance methods for member operations
    public void addMember (Member member) {
        memberRepository.addMember(member);
    }

    public Member getMember (String email) {
        return memberRepository.getMemberByEmail(email);
    }

    //Get getMemberByMemberId
    public Member getMember (int memberId) {
        return memberRepository.getMemberById(memberId);
    }

    public Member getMemberByMemberName(String memberName) {
        return memberRepository.getMemberByMemberName(memberName);
    }

    //hämtar member från Spring Security för att kunna lägga i session
    public String getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }


    //instance methods for map/route operations
    public void addRoute(CreateNewRoute routeToAdd){

        dbRepository.addRoute(routeToAdd); //this will add the route to the database, ignoring the strings storing lat and lng

        //System.out.println("last routeID: " + dbRepository.getLastRouteID());
        Integer routeId = dbRepository.getLastRouteID(); //TODO: test if this works, that is if all the positions added get the correct routeID as foreign key

        String[] tempLatitudes = {};
        String[] tempLongitudes = {};
        tempLatitudes = routeToAdd.getLatitudes().split(", ");
        tempLongitudes = routeToAdd.getLongitudes().split(", ");
        

        if(tempLatitudes.length != tempLongitudes.length){
            System.out.println("Error! Latitude array and longitude array differs in length.");
        }else{
            for(int ii=0; ii<tempLatitudes.length; ii++){
                System.out.println("tempLatitudes[" + ii  + "]: " + tempLatitudes[ii]);
                System.out.println("tempLongitudes[" + ii  + "]: " + tempLongitudes[ii]);
                //add positions to the database. For now, set altitude to 0 in all cases
                Position newPosition = new Position(Double.parseDouble(tempLatitudes[ii]), Double.parseDouble(tempLongitudes[ii]), 0, routeId);
                dbRepository.addPosition(newPosition);
            }
        }
    }

    public Route getRoute(int routeID){
        return dbRepository.getRoute(routeID);
    }

    public List<Route> getAllRoutes(){
        return dbRepository.getAllRoutes();
    }

    public List<Position> getPositions(int routeID){
        //return a list of positions for a given route
        return dbRepository.getPositions(routeID);
    }



    public void setRouteToShow(int routeID){
        Route route = dbRepository.getRoute(routeID);
        List <Position> positions = dbRepository.getPositions(routeID);

        route.setPositions(positions);
        //System.out.println("setRouteToShow: " + route.getRouteId());
        routeToShow = route;
    }

    public List<Route> getAllforMap() {

        List<Route> allRoutes = dbRepository.getAllRoutes();
        List<Position> allPositions = dbRepository.getAllPositions();
        List<Route> routes = new ArrayList<>();

//        for (int i = 0; i < allPositions.size(); i++) {
//            System.out.println("RouteID: " + allPositions.get(i).getRouteId() + ", LatLng: " + allPositions.get(i).getLatitude() +", " + allPositions.get(i).getLongitude());
//
//        }

        for (int i = 0; i < allRoutes.size() ; i++) {
            int routeId = allRoutes.get(i).getRouteId();

            List<Position> routePositions = new ArrayList<>();

            for (int j = 0; j < allPositions.size(); j++) {
                if(routeId == allPositions.get(j).getRouteId()){
                    routePositions.add(allPositions.get(j));
                }
            }

            if(routePositions.size() > 0){
                allRoutes.get(i).setPositions(routePositions);
                routes.add(allRoutes.get(i));
            }

            //allRoutes.get(i).setPositions(routePositions);

//            //Om inga positions hittades till routen så tas routen bort från listan som ska skickas iväg som JSON.
//            if(routePositions.size() == 0){
//                routes.remove(i);
//            }
//            else{
//                routes.get(i).setPositions(routePositions);
//            }
        }

        //Ta bort detta när DB-kopplingen helt fungerar fungerar
//        List<Position> positions2 = new ArrayList<>();
//        positions2.add(new Position(67.900468, 18.516387, null, 99));
//
//        Route route2 = new Route(99, "Kebnekaises sydtopp", "mountaintop", 2034, "Svår", 8000, 0, 12.0, "Fantastisk utsikt från Sveriges högsta berg!", "2020-01-09", null, null, 99);
//        route2.setPositions(positions2);
//
//        List<Position> positions3 = new ArrayList<>();
//        positions3.add(new Position(67.983154, 18.462754, null, 100));
//
//        Route route3 = new Route(100, "Unna Räitasstugan", "poi", 2034, "lätt", 8000, 0, 12.0, "En fin stuga", "2020-01-09", null, null, 99);
//        route3.setPositions(positions3);
//
//        routes.add(route2);
//        routes.add(route3);

        //Slut på ta bort!

        return routes;

    }
}


