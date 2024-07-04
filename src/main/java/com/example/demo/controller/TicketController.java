package com.example.demo.controller;

import com.example.demo.entities.Ticket;
import com.example.demo.service.TicketService;
import com.github.jknack.handlebars.Template;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.demo.config.HandleBarsConfig.hbs;

@Controller
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    @ResponseBody
    public String homeTicket() throws Exception {
        Template template = hbs().compile("home");

        List<Ticket> tickets = ticketService.getAllTickets();

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("tickets", tickets);

        return template.apply(parameters);
    }

    @GetMapping("/getTicketByID/{id}")
    @ResponseBody
    public String getModal(@PathVariable("id") Long id) throws Exception {
        Template template = hbs().compile("ticket_modal");

        Ticket ticket = ticketService.getTicketByID(id);

        return template.apply(ticket);
    }

    @GetMapping("/new")
    @ResponseBody
    public String getModal() throws Exception {
        Template template = hbs().compile("ticket_modal");

        Ticket ticket = new Ticket();

        return template.apply(ticket);
    }

    @PostMapping("/new")
    @ResponseBody
    public String saveTicket(HttpServletRequest request) throws Exception{
        Ticket ticket = new Ticket();

        if(request.getParameter("id") != null){
            if(!request.getParameter("id").isEmpty()){
                ticket.setId(Long.parseLong(request.getParameter("id")));
            }
        }
        if(request.getParameter("title") != null){
            ticket.setTitle(request.getParameter("title"));
        }
        if(request.getParameter("description") != null){
            ticket.setDescription(request.getParameter("description"));
        }

        if(ticket.getId() != null){
            ticketService.saveTicket(ticket);
        }else{
            ticketService.createTicket(ticket);
        }

        return "";
    }
}
