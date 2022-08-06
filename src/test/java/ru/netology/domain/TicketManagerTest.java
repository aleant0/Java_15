package ru.netology.domain;

import org.junit.jupiter.api.Test;
import ru.netology.repository.TicketRepository;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class TicketManagerTest {
    TicketRepository repo = new TicketRepository();
    TicketManager manager = new TicketManager(repo);


    @Test
    public void ShouldFindAndCompareVariousTickets() {
        Ticket ticket1 = new Ticket(1, 1299, "SVO", "KZN", 95);
        Ticket ticket2 = new Ticket(2, 2199, "SVO", "KZN", 95);
        Ticket ticket3 = new Ticket(3, 2499, "SVO", "KZN", 95);
        Ticket ticket4 = new Ticket(4, 2499, "DME", "LED", 95);

        manager.addTicket(ticket2);
        manager.addTicket(ticket3);
        manager.addTicket(ticket1);
        manager.addTicket(ticket4);

        Ticket[] expected = {ticket1, ticket2, ticket3};
        Ticket[] actual = manager.findAll("SVO", "KZN");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void ShouldFindAndCompareEqualTickets() {
        Ticket ticket1 = new Ticket(1, 2199, "SVO", "KZN", 95);
        Ticket ticket2 = new Ticket(2, 2199, "SVO", "KZN", 100);
        Ticket ticket3 = new Ticket(3, 2199, "SVO", "KZN", 105);

        manager.addTicket(ticket3);
        manager.addTicket(ticket2);
        manager.addTicket(ticket1);

        Ticket[] expected = {ticket3, ticket2, ticket1};
        Ticket[] actual = manager.findAll("SVO", "KZN");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void ShouldFindNonExistentTickets() {
        Ticket ticket1 = new Ticket(1, 1299, "SVO", "KZN", 95);
        Ticket ticket2 = new Ticket(2, 2199, "SVO", "KZN", 95);
        Ticket ticket3 = new Ticket(3, 2499, "SVO", "KZN", 95);
        Ticket ticket4 = new Ticket(4, 2499, "DME", "LED", 95);

        manager.addTicket(ticket2);
        manager.addTicket(ticket3);
        manager.addTicket(ticket1);
        manager.addTicket(ticket4);

        Ticket[] expected = {};
        Ticket[] actual = manager.findAll("SVO", "LED");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void ShouldFindAndCompareByTravelTime() {
        TicketTravelTimeComparator timeComparator = new TicketTravelTimeComparator();
        Ticket ticket1 = new Ticket(1, 2199, "SVO", "KZN", 95);
        Ticket ticket2 = new Ticket(2, 2199, "SVO", "KZN", 100);
        Ticket ticket3 = new Ticket(3, 2199, "SVO", "KZN", 105);
        Ticket ticket4 = new Ticket(4, 2199, "SVO", "KZN", 90);

        manager.addTicket(ticket3);
        manager.addTicket(ticket4);
        manager.addTicket(ticket2);
        manager.addTicket(ticket1);

        Ticket[] expected = {ticket4, ticket1, ticket2, ticket3};
        Ticket[] actual = manager.findAll("SVO", "KZN", timeComparator);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void ShouldFindAndCompareByPrice() {
        TicketByPriceAscComparator priceComparator = new TicketByPriceAscComparator();
        Ticket ticket1 = new Ticket(1, 1999, "SVO", "KZN", 95);
        Ticket ticket2 = new Ticket(2, 2199, "SVO", "KZN", 100);
        Ticket ticket3 = new Ticket(3, 2099, "SVO", "KZN", 105);
        Ticket ticket4 = new Ticket(4, 2500, "SVO", "KZN", 90);

        manager.addTicket(ticket3);
        manager.addTicket(ticket4);
        manager.addTicket(ticket2);
        manager.addTicket(ticket1);

        Ticket[] expected = {ticket1, ticket3, ticket2, ticket4};
        Ticket[] actual = manager.findAll("SVO", "KZN", priceComparator);

        assertArrayEquals(expected, actual);
    }
}
