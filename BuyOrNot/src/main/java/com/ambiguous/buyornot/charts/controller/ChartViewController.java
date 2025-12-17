package com.ambiguous.buyornot.charts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/charts")
public class ChartViewController {

    @GetMapping
    public String index() {
        return "chart/index"; // templates/chart/index.html
    }
}
