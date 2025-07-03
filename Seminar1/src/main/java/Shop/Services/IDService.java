package Shop.Services;

import Shop.Utility.PrimeNumberGenerator;

import java.util.HashSet;
import java.util.Set;

public class IDService {
    private Set<Integer> ids;

    public IDService() {
        ids = new HashSet<>();
    }

    public int addId() {
        int newId = PrimeNumberGenerator.getPrimeNumber(1000000000,2000000000);
        int limit = 200000;
        while ((ids.contains(newId) || newId == -1) && limit > 0) {
            newId = PrimeNumberGenerator.getPrimeNumber(1000000000, 2000000000);
            limit--;
        }
        ids.add(newId);
        return newId;
    }

    public void removeId(int id) {
        ids.remove(id);
    }
}
