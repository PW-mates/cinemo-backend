package com.pw.cinema.screening;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ScreeningController {
    @Autowired
    ScreeningService screeningService;
}
