package org.example;
import org.example.PrimeNumberGenerator;
public class IDService {
    //List<Integer> ids = new ArrayList<>();
    Set<Integer> ids = new HashSet<>();


    public int addId() {
        PrimeNumberGenerator primeNumberGenerator = new PrimeNumberGenerator();
        int newId = primeNumberGenerator.getPrimeNumber(1000000000,2000000000);
        ids.add(newId);
    }

    public void removeId(int id) {
        if (ids.contains(id)) ids.remove(id);
    }
}
