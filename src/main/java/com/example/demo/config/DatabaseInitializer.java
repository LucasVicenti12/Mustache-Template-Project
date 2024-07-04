package com.example.demo.config;

import com.example.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {
    private final TicketService ticketService;

    @Autowired
    public DatabaseInitializer(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public void run(String... args) throws Exception {
        ticketService.createTable();
    }
}
