package com.demo.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.demo.dao.bus.BusDAO;
import com.demo.dto.BusDTO;
import com.demo.model.Bus;
import com.demo.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/bus")
public class BusController {

    @Autowired
    private BusDAO busDAO;

    @Autowired
    private Cloudinary cloudinary;

    @GetMapping
    public String list(Model model) {
        List<Bus> buses = busDAO.getAllBuses();
        model.addAttribute("buses", buses);
        return "b3/bus";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("bus", new BusDTO());
        model.addAttribute("busTypes", new String[]{"VIP", "LUXURY", "NORMAL"});
        return "b3/add_bus";
    }

    @PostMapping("/add")
    public String addBus(@Valid @ModelAttribute("bus") BusDTO busDTO, BindingResult result, Model model) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("busTypes", new String[]{"VIP", "LUXURY", "NORMAL"});
            return "b3/add_bus";
        }

        Map<String, Object> imgResult = cloudinary.uploader().upload(busDTO.getImage().getBytes(), ObjectUtils.emptyMap());
        Bus bus = new Bus();
        bus.setLicensePlate(busDTO.getLicensePlate());
        bus.setBusType(busDTO.getBusType());
        bus.setRowSeat(busDTO.getRowSeat());
        bus.setColSeat(busDTO.getColSeat());
        bus.setImage(imgResult.get("url").toString());
        busDAO.addBus(bus);
        return "redirect:/bus";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Bus bus = busDAO.getBusById(id);
        if (bus == null) {
            return "redirect:/bus";
        }
        BusDTO busDTO = new BusDTO();
        busDTO.setId(bus.getId());
        busDTO.setLicensePlate(bus.getLicensePlate());
        busDTO.setBusType(bus.getBusType());
        busDTO.setRowSeat(bus.getRowSeat());
        busDTO.setColSeat(bus.getColSeat());
        busDTO.setCurrentImage(bus.getImage());
        model.addAttribute("bus", busDTO);
        model.addAttribute("busTypes", new String[]{"VIP", "LUXURY", "NORMAL"});
        return "b3/update_bus";
    }

    @PostMapping("/edit/{id}")
    public String updateBus(@PathVariable("id") int id, @Valid @ModelAttribute BusDTO busDTO, BindingResult result) throws IOException{
        if (result.hasErrors()) {
            return "b3/update_bus";
        }
        Bus bus = busDAO.getBusById(id);
        if (bus == null) {
            return "redirect:/bus";
        }
        Map<String, Object> imgResult = cloudinary.uploader().upload(busDTO.getImage().getBytes(), ObjectUtils.emptyMap());
        busDTO.setCurrentImage(imgResult.get("url").toString());
        bus.setLicensePlate(busDTO.getLicensePlate());
        bus.setBusType(busDTO.getBusType());
        bus.setRowSeat(busDTO.getRowSeat());
        bus.setColSeat(busDTO.getColSeat());
        bus.setImage(busDTO.getCurrentImage());
        busDAO.updateBus(bus);
        return "redirect:/bus";
    }

    @GetMapping("/delete/{id}")
    public String deleteBus(@PathVariable("id") int id) {
        busDAO.deleteBus(id);
        return "redirect:/bus";
    }

    @GetMapping("/seats/{id}")
    public String showBusSeats(@PathVariable("id") int id, Model model) {
        Bus bus = busDAO.getBusById(id);
        if (bus == null) {
            return "redirect:/bus";
        }
        List<Seat> seats = busDAO.getSeatsByBusId(id);
        model.addAttribute("bus", bus);
        model.addAttribute("seats", seats);
        return "b3/bus_detail";
    }
}