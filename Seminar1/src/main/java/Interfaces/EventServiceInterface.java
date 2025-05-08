package Interfaces;

import Exceptions.InvalidDateException;
import Exceptions.NegativeNumberException;
import Models.Event;

import java.time.LocalDateTime;

public interface EventServiceInterface {
    void createEvent(String bezeichnung, String ort, LocalDateTime datum, int tickets) throws NegativeNumberException, InvalidDateException;

    Event getEvent(int id);

    void updateEvent(int id, String bezeichnung, String ort, LocalDateTime datum, int tickets) throws NegativeNumberException, InvalidDateException;

    void deleteEvent(int id) throws IllegalArgumentException;

    void printAllEvents();

    void deleteAllEvents();
}
