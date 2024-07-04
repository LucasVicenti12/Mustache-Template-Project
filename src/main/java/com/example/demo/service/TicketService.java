package com.example.demo.service;

import com.example.demo.entities.Ticket;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterBeanMapper(Ticket.class)
public interface TicketService {
    @SqlUpdate(
            "CREATE TABLE IF NOT EXISTS tickets (" +
            "id INT PRIMARY KEY AUTO_INCREMENT," +
            "title VARCHAR(60)," +
            "description VARCHAR(200)" +
            ")"
    )
    void createTable();

    @SqlUpdate("INSERT INTO tickets (title, description) values (:title, :description)")
    void createTicket(@BindBean Ticket ticket);
    @SqlQuery("SELECT * FROM tickets")
    List<Ticket> getAllTickets();

    @SqlUpdate("UPDATE tickets SET title=:title, description=:description WHERE id=:id")
    void saveTicket(@BindBean Ticket ticket);

    @SqlQuery("SELECT * FROM tickets WHERE id=:id")
    Ticket getTicketByID(Long id);
}
