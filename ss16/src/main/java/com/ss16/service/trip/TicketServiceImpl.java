package com.ss16.service.trip;

import com.ss16.model.ticket.Ticket;
import com.ss16.repository.trip.TIcketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService{
    @Autowired
    private TIcketRepository TIcketRepository;

    @Override
    public void insertTicket(Ticket ticket){
        TIcketRepository.insertTicket(ticket);
    }

    @Override
    public List<Ticket> findByUserId(Integer userId){
        return TIcketRepository.findByUserId(userId);
    }

    @Override
    public Ticket findById(Integer id){
        return TIcketRepository.findById(id);
    }
}
