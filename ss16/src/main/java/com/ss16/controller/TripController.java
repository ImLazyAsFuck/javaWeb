package com.ss16.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ss16.dto.bustrip.BustripDto;
import com.ss16.model.bustrip.BusTrip;
import com.ss16.model.user.User;
import com.ss16.model.user.UserRole;
import com.ss16.service.bus.BusService;
import com.ss16.service.bustrip.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/trip")
public class TripController{
    @Autowired
    private TripService tripService;
    @Autowired
    private BusService busService;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private HttpSession session;

    @GetMapping
    public String list(Model model) {
        User user = (User) session.getAttribute("user");
        if(user == null || user.getRole() != UserRole.ADMIN) return "redirect:/login";
        List<BusTrip> trips = tripService.findAll();
        model.addAttribute("trips", trips);
        return "admin/trip_list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        User user = (User) session.getAttribute("user");
        if(user == null || user.getRole() != UserRole.ADMIN) return "redirect:/login";
        model.addAttribute("bustrip", new BustripDto());
        model.addAttribute("buses", busService.findAll());
        return "admin/add_trip";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("bustrip") @Valid BustripDto dto,
                      BindingResult result,
                      Model model) throws IOException {
        User user = (User) session.getAttribute("user");
        if(user == null || user.getRole() != UserRole.ADMIN) return "redirect:/login";
        if (result.hasErrors()) {
            model.addAttribute("buses", busService.findAll());
            return "admin/add_trip";
        }

        if (!dto.getFile().isEmpty()) {
            Map uploadResult = cloudinary.uploader().upload(dto.getFile().getBytes(), ObjectUtils.emptyMap());
            dto.setImage(uploadResult.get("secure_url").toString());
        } else {
            dto.setImage("");
        }

        tripService.insertBusTrip(toEntity(dto));
        return "redirect:/admin/trip";
    }


    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        User user = (User) session.getAttribute("user");
        if(user == null || user.getRole() != UserRole.ADMIN) return "redirect:/login";
        BusTrip trip = tripService.findOne(id);
        model.addAttribute("bustrip", trip);
        model.addAttribute("buses", busService.findAll());
        return "admin/edit_trip";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id,
                       @ModelAttribute("bustrip") @Valid BustripDto dto,
                       BindingResult result,
                       Model model) throws IOException {
        User user = (User) session.getAttribute("user");
        if(user == null || user.getRole() != UserRole.ADMIN) return "redirect:/login";
        if (result.hasErrors()) {
            model.addAttribute("buses", busService.findAll());
            return "admin/edit_trip";
        }

        BusTrip oldTrip = tripService.findOne(id);

        if (dto.getFile() != null && !dto.getFile().isEmpty()) {
            Map uploadResult = cloudinary.uploader().upload(dto.getFile().getBytes(), ObjectUtils.emptyMap());
            dto.setImage(uploadResult.get("secure_url").toString());
        } else{
            dto.setImage(oldTrip.getImage());
        }

        dto.setId(id);
        tripService.update(toEntity(dto));
        return "redirect:/admin/trip";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        User user = (User) session.getAttribute("user");
        tripService.delete(id);
        return "redirect:/admin/trip";
    }

    public static BustripDto toDto(BusTrip trip) {
        BustripDto dto = new BustripDto();
        dto.setId(trip.getId());
        dto.setDeparture(trip.getDeparture());
        dto.setDestination(trip.getDestination());
        dto.setDepartureTime(trip.getDepartureTime());
        dto.setArrivalTime(trip.getArrivalTime());
        dto.setBusId(trip.getBusId());
        dto.setSeatsAvailable(trip.getSeatsAvailable());
        dto.setImage(trip.getImage());
        return dto;
    }

    public static BusTrip toEntity(BustripDto dto) {
        BusTrip trip = new BusTrip();
        trip.setId(dto.getId());
        trip.setDeparture(dto.getDeparture());
        trip.setDestination(dto.getDestination());
        trip.setDepartureTime(dto.getDepartureTime());
        trip.setArrivalTime(dto.getArrivalTime());
        trip.setBusId(dto.getBusId());
        trip.setSeatsAvailable(dto.getSeatsAvailable());
        trip.setImage(dto.getImage());
        return trip;
    }
}
