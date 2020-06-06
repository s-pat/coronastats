package io.sohan.coronatracker.controllers;

import io.sohan.coronatracker.services.CoronaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home {

    @Autowired
    CoronaDataService coronaDataService;

    @GetMapping("/")
    public String home(Model model){

        model.addAttribute("locationStats", coronaDataService.getAllStats());
        model.addAttribute("dailyCount", coronaDataService.getCaseCount());
        return "home";



    }


}
