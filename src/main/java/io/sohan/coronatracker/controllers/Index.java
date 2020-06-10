package io.sohan.coronatracker.controllers;

import io.sohan.coronatracker.models.LocationData;
import io.sohan.coronatracker.services.CoronaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Index {

    @Autowired
    CoronaDataService coronaDataService;

    @GetMapping("/")
    public String index(Model model){
        List<String> countryNames = new ArrayList<String>();
        coronaDataService.getAllStats().forEach(
                country -> {
                    if(country.getNewCases() > 500){
                        countryNames.add(country.getCovidCountry());
                    }
                }
        );
        List<Integer> countryCount = new ArrayList<Integer>();
        coronaDataService.getAllStats().forEach(
                country -> { if(country.getNewCases() > 500)
                    countryCount.add(country.getNewCases());
                });
        model.addAttribute("locationStats", coronaDataService.getAllStats());
        model.addAttribute("dailyCount", coronaDataService.getCaseCount());

        model.addAttribute("countryNames", countryNames);
        model.addAttribute("countryCount", countryCount);
        return "index";




    }
    @GetMapping("/indexFiltered")
    public String indexFiltered(Model model){

        model.addAttribute("locationStats", coronaDataService.getAllStats());
        model.addAttribute("dailyCount", coronaDataService.getCaseCount());
        return "indexFiltered";



    }
}
