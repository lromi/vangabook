package com.lrom.controllers;

import com.lrom.domain.Competitor;
import com.lrom.domain.enums.Role;
import com.lrom.services.CompetitorService;
import com.lrom.services.EmailService;
import com.lrom.services.impementations.CompetitorServiceImpl;
import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


@Controller
    public class VangaController {

    private PasswordEncoder bCryptPasswordEncoder;
    private CompetitorServiceImpl competitorService;
    private EmailService emailService;


    @Autowired
    public VangaController(PasswordEncoder bCryptPasswordEncoder, CompetitorServiceImpl competitorService, EmailService emailService) {

        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.competitorService = competitorService;
        this.emailService = emailService;
    }

    // Return registration form template
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showRegistrationPage(ModelAndView modelAndView, Competitor competitor) {
        modelAndView.addObject("competitor", competitor);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    // Process form input data
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid Competitor competitor, BindingResult bindingResult, HttpServletRequest request) {

        // Lookup user in database by e-mail
        Competitor userExists = competitorService.findByEmail(competitor.getEmail());

        System.out.println(userExists);

        if (userExists != null) {
            modelAndView.addObject("alreadyRegisteredMessage", "Oops!  There is already a user registered with the email provided.");
            modelAndView.setViewName("register");
            bindingResult.reject("email");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("register");
        } else { // new user so we create user and send confirmation e-mail

            // Disable user until they click on confirmation link in email
            competitor.setEnabled(false);

            // Generate random 36-character string token for confirmation link
            competitor.setConfirmationToken(UUID.randomUUID().toString());
            competitor.setAccountNonExpired(true);
            competitor.setAccountNonLocked(true);
            competitor.setCredentialsNonExpired(true);
            competitor.setAuthorities(Role.USER);
            competitor.setNetCash(1000);
            competitor.setReservedCash(0);

            competitorService.saveCompetitor(competitor);

            String appUrl = request.getScheme() + "://" + request.getServerName();

            SimpleMailMessage registrationEmail = new SimpleMailMessage();
            registrationEmail.setTo(competitor.getEmail());
            registrationEmail.setSubject("Registration Confirmation");
            registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
                    + appUrl + ":8080/confirm?token=" + competitor.getConfirmationToken());
            registrationEmail.setFrom("noreply@domain.com");

            emailService.sendEmail(registrationEmail);

            modelAndView.addObject("confirmationMessage", "A confirmation e-mail has been sent to " + competitor.getEmail());
            modelAndView.setViewName("register");
        }

        return modelAndView;
    }

    // Process confirmation link
    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public ModelAndView showConfirmationPage(ModelAndView modelAndView, @RequestParam("token") String token) {

        Competitor user = competitorService.findByConfirmationToken(token);

        if (user == null) { // No token found in DB
            modelAndView.addObject("invalidToken", "Oops!  This is an invalid confirmation link.");
        } else { // Token found
            modelAndView.addObject("confirmationToken", user.getConfirmationToken());
        }

        modelAndView.setViewName("confirm");
        return modelAndView;
    }

    // Process confirmation link
    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public ModelAndView processConfirmationForm(ModelAndView modelAndView, BindingResult bindingResult, @RequestParam Map requestParams, RedirectAttributes redir) {

        modelAndView.setViewName("confirm");

        Zxcvbn passwordCheck = new Zxcvbn();

        Strength strength = passwordCheck.measure((requestParams.get("password")).toString());

        if (strength.getScore() < 3) {
            bindingResult.reject("password");

            redir.addFlashAttribute("errorMessage", "Your password is too weak.  Choose a stronger one.");

            modelAndView.setViewName("redirect:confirm?token=" + requestParams.get("token"));
            System.out.println(requestParams.get("token"));
            return modelAndView;
        }

        // Find the user associated with the reset token
        Competitor user = competitorService.findByConfirmationToken((requestParams.get("token")).toString());

        // Set new password
        user.setPassword(bCryptPasswordEncoder.encode((requestParams.get("password")).toString()));

        // Set user to enabled
        user.setEnabled(true);

        // Save user
        competitorService.saveCompetitor(user);

        modelAndView.addObject("successMessage", "Your password has been set!");
        return modelAndView;
    }



        @RequestMapping(value= "/start")
        public String index(Model model){
            return "index";
        }

    @RequestMapping(value = "/")
    public String index2(Model model, Principal principal) {
        if (!(principal == null)) {
            Optional<Competitor> competitor = competitorService.findByUsername(principal.getName());
            model.addAttribute("competitor", competitor);
        }
        return "index";
    }


    @RequestMapping("/login")
    public String getLogin(@RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "logout", required = false) String logout,
                           Model model) {
        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);
        return "login";
    }





}

