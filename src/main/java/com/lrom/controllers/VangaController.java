package com.lrom.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
    public class VangaController {
        @RequestMapping(value= "/start")
        public String index(Model model){

            return "index";
        }
    @RequestMapping(value= "/mockup", method = RequestMethod.GET)
    public String  about(Model model) {

        return "mockup";
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

