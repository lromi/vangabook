package com.lrom.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
    public class VangaController {
        @RequestMapping(value= "/start")
        public String index(Model model){

            return "index";
        }
    @RequestMapping(value= "/about", method = RequestMethod.GET)
    public String  about(Model model) {

        return "about";
    }


}

