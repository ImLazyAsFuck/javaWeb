package com.ss9.controller;

import com.ss9.service.seatservice.SeatService;
import com.ss9.service.ticketservice.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class TicketController {
    @Autowired
    TicketService ticketService;
    SeatService seatService;

    @GetMapping("/seats/{id}")
    public ModelAndView ticket(){
        return new ModelAndView("seats").addObject("seats", seatService.findAll());
    }
}
