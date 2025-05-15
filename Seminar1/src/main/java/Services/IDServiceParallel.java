package Services;

import Utility.ParallelPrimeNumberGenerator;
import Utility.PrimeNumberGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IDServiceParallel {
    private Set<Integer> ids;
    private List<Integer> preGeneratedIds;

    public IDServiceParallel() {
        ids = new HashSet<>();
        preGeneratedIds = new ArrayList<>();
    }

    public int addId() throws Exception{

        if (preGeneratedIds.isEmpty()) {
            fillPreGeneratedIds();
        }
        if (preGeneratedIds.isEmpty()) {
            throw new Exception("generation not possible");
        }

        int currentId = preGeneratedIds.get(0);
        preGeneratedIds.remove(0);
        return currentId;


    }

    public void fillPreGeneratedIds() {

        int threadAmount = 1;
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadAmount; i++) {
            ParallelPrimeNumberGenerator generator = new ParallelPrimeNumberGenerator(1000000000, 2000000000, ids, preGeneratedIds);
            threads.add(generator);
            generator.start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }


    }



    public void removeId(int id) {
        ids.remove(id);
    }
}
