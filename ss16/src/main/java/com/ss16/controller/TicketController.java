package com.ss16.controller;

import com.ss16.dto.ticket.TicketDto;
import com.ss16.model.bustrip.BusTrip;
import com.ss16.model.ticket.Ticket;
import com.ss16.model.user.User;
import com.ss16.service.bustrip.BustripService;
import com.ss16.service.trip.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private BustripService busTripService;

    @GetMapping("/book/{tripId}")
    public String showBookingForm(@PathVariable Long tripId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        BusTrip trip = busTripService.findOne(tripId);
        model.addAttribute("trip", trip);

        TicketDto ticketDto = new TicketDto();
        ticketDto.setTripBusId(tripId);
        model.addAttribute("ticketDto", ticketDto);

        return "user/book_ticket";
    }


    @PostMapping("/book/{tripId}")
    public String bookTicket(@PathVariable Long tripId,
                             @ModelAttribute("ticketDto") @Valid TicketDto ticketDto,
                             BindingResult result,
                             HttpSession session,
                             Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        BusTrip trip = busTripService.findOne(tripId);
        if (result.hasErrors()) {
            ticketDto.setTripBusId(tripId);
            model.addAttribute("ticketDto", ticketDto);
            model.addAttribute("trip", trip);
            return "user/book_ticket";
        }

        ticketDto.setUserId(user.getId());
        ticketDto.setTripBusId(tripId);
        Ticket ticket = toEntity(ticketDto);
        ticketService.insertTicket(ticket);

        return "redirect:/ticket/my";
    }


    @GetMapping("/my")
    public String myTickets(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        List<Ticket> tickets = ticketService.findByUserId(Math.toIntExact(user.getId()));
        model.addAttribute("tickets", tickets);
        return "user/ticket_list";
    }

    @GetMapping("/detail/{id}")
    public String ticketDetail(@PathVariable Long id, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        Ticket ticket = ticketService.findById(Math.toIntExact(id));
        if (ticket == null || ticket.getUserId() != user.getId()) {
            return "redirect:/ticket/my";
        }
        TicketDto ticketDto = toDto(ticket);
        model.addAttribute("ticket", ticketDto);
        return "user/ticket_detail";
    }


    public static TicketDto toDto(Ticket ticket) {
        TicketDto dto = new TicketDto();
        dto.setId(ticket.getId());
        dto.setUserId(ticket.getUserId());
        dto.setTripBusId(ticket.getTripBusId());
        dto.setListSeat(ticket.getListSeat());
        dto.setTotalMoney(ticket.getTotalMoney());
        dto.setDepartureDate(ticket.getDepartureDate());
        return dto;
    }

    public static Ticket toEntity(TicketDto dto) {
        Ticket ticket = new Ticket();
        ticket.setId(dto.getId());
        ticket.setUserId(dto.getUserId());
        ticket.setTripBusId(dto.getTripBusId());
        ticket.setListSeat(dto.getListSeat());
        ticket.setTotalMoney(dto.getTotalMoney());
        ticket.setDepartureDate(dto.getDepartureDate());
        return ticket;
    }
}
