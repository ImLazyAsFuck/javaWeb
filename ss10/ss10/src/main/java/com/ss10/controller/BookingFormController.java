package com.ss10.controller;

import com.ss10.model.Seat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/booking")
public class BookingFormController{
    private static List<Seat> seats = IntStream.rangeClosed(1, 10)
            .mapToObj(i -> new Seat(i, "S" + i, false, 100.0))
            .collect(Collectors.toList());

    @GetMapping
    public String bookingForm(Model model){
        model.addAttribute("seats", seats);
        return "/booking/bookingForm";
    }

    @PostMapping
    public String bookingForm(Model model, int[] selectedSeats){
        List<Seat> selected = new ArrayList<>();
        for(int i : selectedSeats){
            selected.add(seats.get(i));
        }
        model.addAttribute("seats", selected);
        return "/booking/bookingResult";
    }
}
