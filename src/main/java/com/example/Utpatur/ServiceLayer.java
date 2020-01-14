package com.example.Utpatur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceLayer {
    @Autowired
    DbRepository dbRepository;

    @Autowired
    MemberRepository memberRepository;



    //instance methods for member operations
    public void addMember (Member member) {
        memberRepository.addMember(member);
    }

    public Member getMember (String email) {
        return memberRepository.getMemberByEmail(email);
    }

    //Get memberByMemberId

    //instance methods for map/route operations
    public void addRoute(CreateNewRoute routeToAdd){

        dbRepository.addRoute(routeToAdd); //this will add the route to the database, ignoring the strings storing lat and lng

        System.out.println("last routeID: " + dbRepository.getLastRouteID());
        Integer routeId = dbRepository.getLastRouteID(); //TODO: test if this works, that is if all the positions added get the correct routeID as foreign key

        String[] tempLatitudes;
        String[] tempLongitudes;
        tempLatitudes = routeToAdd.getLatitudes().split(", ");
        tempLongitudes = routeToAdd.getLongitudes().split(", ");

        if(tempLatitudes.length != tempLongitudes.length){
            System.out.println("Error! Latitude array and longitude array differs in length.");
        }else{
            for(int ii=0; ii<tempLatitudes.length; ii++){
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


    public Route mergeRouteAndPosition(int id) {

        Route route =  getRoute(id);
        List<Position> positions = getPositions(id);

        route.setPositions(positions);

        return route;

    }

    public List<Route> getAllforMap() {

        List<Route> routes = dbRepository.getAllRoutes();
        List<Position> allPositions = dbRepository.getAllPositions();

        for (int i = 0; i < routes.size() ; i++) {
            int routeId = routes.get(i).getRouteId();

            List<Position> routePositions = new ArrayList<>();

            for (int j = 0; j < allPositions.size(); j++) {
                if(routeId == allPositions.get(j).getRouteId()){
                    routePositions.add(allPositions.get(j));
                }
            }

            routes.get(i).setPositions(routePositions);
        }

        return routes;

    }
}


