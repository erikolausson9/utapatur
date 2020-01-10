package com.example.Utpatur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class MemberController {

    @Autowired
    DbRepository dbRepository;

    @GetMapping("/profil")
    String profil(Model model) {

        return "profile";
    }


    @PostMapping("/inloggning")
    String inloggning(HttpSession httpSession, @RequestParam String email, @RequestParam String password) {

        Member member = dbRepository.getMemberEmail("email");
        //check if member exist or not
        //
        return "profile";
    }


     /*@GetMapping("/addMember")
    String addMember(Model model) {
        model.addAttribute("member", new Member());

        return "member-registration";
    }

    @PostMapping("/addMember")
    String addMember(HttpSession session, Model model, @Valid Member member, BindingResult result) {
        if (result.hasErrors()) {
            return "member-registration";
        }

        if(serviceLayer.getMember(member.getMemberName()) != null) {
            model.addAttribute("memberExists", true);
            return "addMember";
        }
        model.addAttribute("member", member);
        serviceLayer.addMember(member);

        List<Member> members = (List<Member>)session.getAttribute("members");
        if (members == null) {
            members = new ArrayList<>();
            session.setAttribute("members", members);
            }

        members.add(member);
        model.addAttribute("noErrors", true);

        return "member-registration";
    }*/

}
