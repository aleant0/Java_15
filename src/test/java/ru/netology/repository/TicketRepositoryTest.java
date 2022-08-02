package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;

import  static org.junit.jupiter.api.Assertions.*;

public class TicketRepositoryTest {
    TicketRepository repo = new TicketRepository();

    Ticket ticket1 = new Ticket(1, 1299, "SVO", "KZN", 95);
    Ticket ticket2 = new Ticket(2, 2199, "VKO", "KZN", 95);
    Ticket ticket3 = new Ticket(3, 2499, "DME", "KZN", 95);

    @Test
    public void ShouldSave() {
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);

        Ticket[] expected = {ticket1, ticket2, ticket3};
        Ticket[] actual = repo.getTickets();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void ShouldRemoveById() {
        repo.save(ticket1);
        repo.save(ticket2);
        repo.save(ticket3);
        repo.removeById(2);

        Ticket[] expected = {ticket1, ticket3};
        Ticket[] actual = repo.getTickets();
    }
}
