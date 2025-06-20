package com.ss16.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ss16.dto.bustrip.BustripDto;
import com.ss16.model.bustrip.BusTrip;
import com.ss16.model.user.User;
import com.ss16.model.user.UserRole;
import com.ss16.service.bus.BusService;
import com.ss16.service.bustrip.BustripService;
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
@RequestMapping("/admin/bustrip")
public class BustripController{
    @Autowired
    private BustripService bustripService;
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
        List<BusTrip> bustrips = bustripService.findAll();
        model.addAttribute("bustrips", bustrips);
        return "admin/bustrip_list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        User user = (User) session.getAttribute("user");
        if(user == null || user.getRole() != UserRole.ADMIN) return "redirect:/login";
        model.addAttribute("bustrip", new BustripDto());
        model.addAttribute("buses", busService.findAll());
        return "admin/add_bustrip";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("bustrip") @Valid BustripDto dto,
                      BindingResult result,
                      Model model) throws IOException {
        User user = (User) session.getAttribute("user");
        if(user == null || user.getRole() != UserRole.ADMIN) return "redirect:/login";
        if (result.hasErrors()) {
            model.addAttribute("buses", busService.findAll());
            return "admin/add_bustrip";
        }

        if (!dto.getFile().isEmpty()) {
            Map uploadResult = cloudinary.uploader().upload(dto.getFile().getBytes(), ObjectUtils.emptyMap());
            dto.setImage(uploadResult.get("secure_url").toString());
        } else {
            dto.setImage("");
        }

        bustripService.insertBusTrip(toEntity(dto));
        return "redirect:/admin/bustrip";
    }


    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        User user = (User) session.getAttribute("user");
        if(user == null || user.getRole() != UserRole.ADMIN) return "redirect:/login";
        BusTrip bustrip = bustripService.findOne(id);
        model.addAttribute("bustrip", bustrip);
        model.addAttribute("buses", busService.findAll());
        return "admin/edit_bustrip";
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
            return "admin/edit_bustrip";
        }

        BusTrip oldTrip = bustripService.findOne(id);

        if (dto.getFile() != null && !dto.getFile().isEmpty()) {
            Map uploadResult = cloudinary.uploader().upload(dto.getFile().getBytes(), ObjectUtils.emptyMap());
            dto.setImage(uploadResult.get("secure_url").toString());
        } else{
            dto.setImage(oldTrip.getImage());
        }

        dto.setId(id);
        bustripService.update(toEntity(dto));
        return "redirect:/admin/trip";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        User user = (User) session.getAttribute("user");
        bustripService.delete(id);
        return "redirect:/admin/trip";
    }

    public static BustripDto toDto(BusTrip bustrip) {
        BustripDto dto = new BustripDto();
        dto.setId(bustrip.getId());
        dto.setDeparture(bustrip.getDeparture());
        dto.setDestination(bustrip.getDestination());
        dto.setDepartureTime(bustrip.getDepartureTime());
        dto.setArrivalTime(bustrip.getArrivalTime());
        dto.setBusId(bustrip.getBusId());
        dto.setSeatsAvailable(bustrip.getSeatsAvailable());
        dto.setImage(bustrip.getImage());
        return dto;
    }

    public static BusTrip toEntity(BustripDto dto) {
        BusTrip bustrip = new BusTrip();
        bustrip.setId(dto.getId());
        bustrip.setDeparture(dto.getDeparture());
        bustrip.setDestination(dto.getDestination());
        bustrip.setDepartureTime(dto.getDepartureTime());
        bustrip.setArrivalTime(dto.getArrivalTime());
        bustrip.setBusId(dto.getBusId());
        bustrip.setSeatsAvailable(dto.getSeatsAvailable());
        bustrip.setImage(dto.getImage());
        return bustrip;
    }
}
