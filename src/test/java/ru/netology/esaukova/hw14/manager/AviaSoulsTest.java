package ru.netology.esaukova.hw14.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.esaukova.hw14.domain.Ticket;

import java.util.Comparator;

public class AviaSoulsTest {

    AviaSouls avia = new AviaSouls();
    Ticket ticket1 = new Ticket("Спб", "Москва", 5_000, 12, 14);
    Ticket ticket2 = new Ticket("Москва", "Спб", 5_000, 17, 20);
    Ticket ticket3 = new Ticket("Москва", "Анталья", 20_120, 9, 14);
    Ticket ticket4 = new Ticket("Москва", "Спб", 7_200, 14, 15);
    Ticket ticket5 = new Ticket("Москва", "Спб", 6_300, 17, 18);
    Ticket ticket6 = new Ticket("Москва", "Спб", 6_400, 17, 19);

    @BeforeEach
    public void aviaAddTicket() {
        avia.add(ticket1);
        avia.add(ticket2);
        avia.add(ticket3);
        avia.add(ticket4);
        avia.add(ticket5);
        avia.add(ticket6);
    }

    @Test
    public void findAllTest() {
        Ticket[] expected = {ticket1, ticket2, ticket3, ticket4, ticket5, ticket6};
        Ticket[] actual = avia.findAll();

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void compareToWhenPriceT1LessThanPriceT2Test() {
        Assertions.assertEquals(-1, ticket6.compareTo(ticket4));

    }

    @Test
    public void compareToWhenPriceT1MoreThanPriceT2Test() {
        Assertions.assertEquals(1, ticket3.compareTo(ticket1));

    }

    @Test
    public void compareToWhenPriceT1EqualPriceT2Test() {
        Assertions.assertEquals(0, ticket1.compareTo(ticket2));

    }

    @Test
    public void searchByCityFromCityToTest() {
        Ticket[] expected = {ticket3};
        Ticket[] actual = avia.search("Москва", "Анталья");

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void searchAndSortedByPriceTest() {
        Ticket[] expected = {ticket2, ticket5, ticket6, ticket4};
        Ticket[] actual = avia.search("Москва", "Спб");

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void searchAndSortedByFlightTimeTest() {
        Comparator<Ticket> comparator = new TicketTimeComparator();
        Ticket[] expected = {ticket4, ticket5, ticket6, ticket2};
        Ticket[] actual = avia.searchAndSortBy("Москва", "Спб", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }

}
