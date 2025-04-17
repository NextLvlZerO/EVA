package org.example;
import org.example.PrimeNumberGenerator;

import java.util.HashSet;
import java.util.Set;

public class IDService {
    //List<Integer> ids = new ArrayList<>();
    Set<Integer> ids = new HashSet<>();


    public int addId() {
        int newId = PrimeNumberGenerator.getPrimeNumber(1000000000,2000000000);
        ids.add(newId);
        return newId;
    }

    public void removeId(int id) {
        if (ids.contains(id)) ids.remove(id);
    }
}
