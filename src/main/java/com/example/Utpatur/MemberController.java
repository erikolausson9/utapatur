package com.example.Utpatur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MemberController {

   @Autowired
    MemberRepository memberRepository;

   @Autowired
   DbRepository dbRepository;

   @Autowired
   PasswordEncoder encoder;

   @Autowired
    ServiceLayer serviceLayer;


   //tillfällig/tas bort när profil/memberId fungerar
   @GetMapping("/profil")
   String profilRightNow(HttpSession session, Model model, Principal principal) {
       Member member = memberRepository.getMemberByMemberName(principal.getName());

       System.out.println("Vi har nått profilvyn");
       //Member member = serviceLayer.getMemberByMemberName(serviceLayer.getUser());
       //Member user = (Member) session.getAttribute("user");
       session.setAttribute("memberKey", member);
       model.addAttribute("memberKey", member);

       session.setAttribute("routeList", memberRepository.getRoutesByMemberId(member.getMemberId()));

       int listSize = memberRepository.getRoutesByMemberId(member.getMemberId()).size();
       session.setAttribute("routeListSize", listSize);

       model.addAttribute("routeList", memberRepository.getRoutesByMemberId(member.getMemberId()));

       return "profil";
   }

   @PostMapping("/taBortRutt")
   String taBortRutt (@ModelAttribute Route route, Model model) {

       model.addAttribute("route", route);

       dbRepository.deleteRoute(route.getRouteId());
       dbRepository.deletePositions(route.getRouteId());

       return"redirect:/profil";
   }

    @GetMapping("/login")
    public String login() {

        return "login";
    }

   /* session.setAttribute("user", upr.findByUsername(userService.getUser()));
    UserProfile user = (UserProfile) httpSession.getAttribute("user");*/

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }



     @GetMapping("/registrering")
    String addMember(Model model) {
        model.addAttribute("member", new Member());

        return "registrering";
    }

    @PostMapping("/registrering")
    String addMember(HttpSession session, Model model, @Valid Member member, BindingResult result) {
        if (result.hasErrors()) {
            return "registrering";
        }

        if(serviceLayer.getMember(member.getEmail()) != null) {
            model.addAttribute("memberExists", true);
            return "registrering";
        }
        member.setPassword(encoder.encode(member.getPassword()));
        model.addAttribute("member", member);
        serviceLayer.addMember(member);

        return "login";
    }

}
