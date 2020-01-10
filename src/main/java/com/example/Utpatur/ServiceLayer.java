package com.example.Utpatur;

import com.example.Utpatur.DbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    }
}


