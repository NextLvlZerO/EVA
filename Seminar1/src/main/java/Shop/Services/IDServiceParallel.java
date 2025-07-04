package Shop.Services;

import Shop.Utility.ParallelPrimeNumberGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class IDServiceParallel {
    private ConcurrentLinkedQueue<Integer> ids;
    private List<Integer> preGeneratedIds;

    public IDServiceParallel() {
        ids = new ConcurrentLinkedQueue<>();

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

        int threadAmount = 8;
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
