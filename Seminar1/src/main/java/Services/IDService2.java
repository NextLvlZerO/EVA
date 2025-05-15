package Services;

import Utility.PrimeNumberGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IDService2 {
    private Set<Integer> ids;
    private List<Integer> preGeneratedIds;

    public IDService2() {
        ids = new HashSet<>();
        preGeneratedIds = new ArrayList<>();
    }

    public int addId() {

        if (!preGeneratedIds.isEmpty()) {
            int currentId = preGeneratedIds.get(0);
            preGeneratedIds.remove(0);
            return currentId;
        }

        for (int i = 0; i < 10; i++) {
            int newId = PrimeNumberGenerator.getPrimeNumber(1000000000,2000000000);
            int limit = 20000;
            while ((ids.contains(newId) || newId == -1) && limit > 0) {
                newId = PrimeNumberGenerator.getPrimeNumber(1000000000, 2000000000);
                limit--;
            }
            preGeneratedIds.add(newId);
        }
        int currentId = preGeneratedIds.get(0);
        preGeneratedIds.remove(0);
        ids.add(currentId);
        return currentId;
    }

    public void removeId(int id) {
        ids.remove(id);
    }
}
