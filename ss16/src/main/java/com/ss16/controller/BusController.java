package com.ss16.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ss16.dto.bus.BusDto;
import com.ss16.model.bus.Bus;
import com.ss16.model.bus.BusType;
import com.ss16.service.bus.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/admin/bus")
public class BusController{
    @Autowired
    private BusService busService;

    @Autowired
    private HttpSession session;

    @Autowired
    private Cloudinary cloudinary;

    @GetMapping
    public String list(Model model){
        if(session.getAttribute("user") == null) return "redirect:/login";
        model.addAttribute("buses", busService.findAll());
        return "admin/bus_list";
    }

    @GetMapping("/add")
    public String newBus(Model model, BusDto busDto){
        if(session.getAttribute("user") == null) return "redirect:/login";
        model.addAttribute("busDto", new BusDto());
        return "admin/add_bus";
    }

    @GetMapping("/edit")
    public String editBus(Model model, BusDto busDto){
        if(session.getAttribute("user") == null) return "redirect:/login";
        Bus bus = busService.findOne(busDto.getId());
        if(bus == null) return "redirect:/admin/bus";
        busDto.setLicensePlate(bus.getLicensePlate());
        busDto.setBusType(String.valueOf(bus.getBusType()));
        busDto.setRowSeat(bus.getRowSeat());
        busDto.setColSeat(bus.getColSeat());
        busDto.setImage(bus.getImage());
        model.addAttribute("busDto", busDto);
        return "admin/edit_bus";
    }

    @PostMapping("/delete")
    public String deleteBus(Model model, @RequestParam("id") Long id){
        if(session.getAttribute("user") == null) return "redirect:/login";
        busService.delete(id);
        return "redirect:/admin/bus";
    }

    @PostMapping("/add")
    public String saveBus(@Valid @ModelAttribute("busDto") BusDto busDto,
                          BindingResult bindingResult,
                          Model model) throws IOException {
        if (session.getAttribute("user") == null)
            return "redirect:/login";

        if (bindingResult.hasErrors()){
            return "admin/add_bus";
        }
        if(busDto.getFile() != null && !busDto.getFile().isEmpty()){
            Map uploadResult = cloudinary.uploader().upload(
                    busDto.getFile().getBytes(),
                    ObjectUtils.emptyMap()
            );
            String imageUrl = uploadResult.get("secure_url").toString();
            busDto.setImage(imageUrl);
        }
        else busDto.setImage("");
        Bus bus = new Bus(
                busDto.getId(),
                busDto.getLicensePlate(),
                BusType.valueOf(busDto.getBusType()),
                busDto.getRowSeat(),
                busDto.getColSeat(),
                busDto.getRowSeat() + busDto.getColSeat(),
                busDto.getImage()
        );
        busService.save(bus);

        return "redirect:/admin/bus";
    }

    @PostMapping("/edit")
    public String updateBus(@Valid @ModelAttribute("busDto") BusDto busDto,
                           BindingResult bindingResult,
                           Model model) throws IOException{
        if(session.getAttribute("user") == null)
            return "redirect:/login";
        if(bindingResult.hasErrors())
            return "admin/edit_bus";
        Bus bus = busService.findOne(busDto.getId());
        if(bus == null) return "redirect:/admin/bus";
        if(busDto.getImage() == null || busDto.getImage().isEmpty()) busDto.setImage(bus.getImage());
        else{
            Map uploadResult = cloudinary.uploader().upload(
                    busDto.getFile().getBytes(),
                    ObjectUtils.emptyMap()
            );
            String imageUrl = uploadResult.get("secure_url").toString();
            busDto.setImage(imageUrl);
        }
        bus.setLicensePlate(busDto.getLicensePlate());
        bus.setBusType(BusType.valueOf(busDto.getBusType()));
        bus.setRowSeat(busDto.getRowSeat());
        bus.setColSeat(busDto.getColSeat());
        bus.setImage(busDto.getImage());
        busService.update(bus);
        return "redirect:/admin/bus";
    }
}
