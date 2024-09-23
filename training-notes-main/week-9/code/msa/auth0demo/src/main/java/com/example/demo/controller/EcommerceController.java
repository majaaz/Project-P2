package com.example.demo.controller;



import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EcommerceController {

    @GetMapping("/home")
    public String home() {
    	System.out.println("home");
            return "home";  
    }
    @GetMapping("/home1")
    public String home1() {
            return "home1";  
    }
    @GetMapping("/home2")
    public String home2() {
            return "home2";  
    }

    @GetMapping("/categories")
    public String categories() {
        return "categories"; 
    }

    @GetMapping("/cart")
    public String cart() {
        return "cart";  
    }

    @GetMapping("/orders")
    public String orders() {
        return "orders";  
    }

    @GetMapping("/login")
    public String login() {
        return "login";  
    }

 

    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal OidcUser oidcUser) {
//        // Get authenticated user details
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        
//        if (principal instanceof UserDetails) {
//            String email = ((UserDetails) principal).getUsername(); // This should be the email
//            model.addAttribute("email", email);
//        } else {
//            model.addAttribute("email", "anonymousUser");
//        }

    	model.addAttribute("profile", oidcUser.getClaims());
    	if (oidcUser.getUserInfo() != null) {
            OidcUserInfo userInfo = oidcUser.getUserInfo();
            System.out.println("User Info Claims: " + userInfo.getClaims());
            System.out.println("User Email: " + userInfo.getEmail());  // Example to get the email
            System.out.println("User Name: " + userInfo.getFullName()); 
            System.out.println("User Name: " + userInfo.getGender()); // Example to get the name
        } else {
            System.out.println("No UserInfo available");
        }

        // Print specific claims from OidcIdToken
        OidcIdToken idToken = oidcUser.getIdToken();
        System.out.println("ID Token Claims: " + idToken.getClaims());
        System.out.println("ID Token Subject: " + idToken.getSubject());  // Example to get the subject
        System.out.println("ID Token Issuer: " + idToken.getIssuer()); 
        System.out.println(idToken.getTokenValue());
    	
    	
        return "profile"; // Return the view name
    }

}

