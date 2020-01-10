package com.example.Utpatur;

import com.example.Utpatur.DbRepository;
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

    //instance methods for map/route operations
    public void addRoute(CreateNewRoute routeToAdd){

        dbRepository.addRoute(routeToAdd);

        Integer routeId = 1; //TODO: when fetching a route from database works, this should be used to find correct routeID for the new route


        String[] tempLatitudes;
        String[] tempLongitudes;
        tempLatitudes = routeToAdd.getLatitudes().split(", ");
        tempLongitudes = routeToAdd.getLongitudes().split(", ");


        if(tempLatitudes.length != tempLongitudes.length){
            System.out.println("Error! Latitude array and longitude array differs in length.");
        }else{
            for(int ii=0; ii<tempLatitudes.length; ii++){
                Position newPosition = new Position(Double.parseDouble(tempLatitudes[ii]), Double.parseDouble(tempLongitudes[ii]), 0, routeId);
                dbRepository.addPosition(newPosition);
            }

        }



    }
}


