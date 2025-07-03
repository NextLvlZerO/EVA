package Shop.Utility;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;

public class ParallelPrimeNumberGenerator extends Thread {

    private int l,r;
    private final List<Integer> preGeneratedIds;
    private ConcurrentLinkedQueue<Integer> ids;

    public ParallelPrimeNumberGenerator(int l, int r, ConcurrentLinkedQueue<Integer> ids, List<Integer> preGeneratedIds) {
        this.l = l;
        this.r = r;
        this.ids = ids;
        this.preGeneratedIds = preGeneratedIds;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20000; i++) {
            int randomNumber = ThreadLocalRandom.current().nextInt(l, r + 1);
            if (!PrimeNumberGenerator.isPrim(randomNumber)) {
                continue;
            }
            if (preGeneratedIds.size() > 1000 && ids.contains(randomNumber) && preGeneratedIds.contains(randomNumber)) {
                break;
            }

            synchronized (preGeneratedIds) {
                if (!preGeneratedIds.contains(randomNumber)) {
                    preGeneratedIds.add(randomNumber);
                }
            }

        }
    }

}
