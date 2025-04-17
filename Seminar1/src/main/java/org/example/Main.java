package org.example;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args){
        Event valorantEvent = new Event(12, "valorant","Berlin", LocalDateTime.of(2025, 4, 16, 14, 30, 0),23);
        System.out.println(valorantEvent);
        PrimeNumberGenerator primeNumberGenerator = new PrimeNumberGenerator();
        System.out.println(primeNumberGenerator.getPrimeNumber(10,20));
    }
}
