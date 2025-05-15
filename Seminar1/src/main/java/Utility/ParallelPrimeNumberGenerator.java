package Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import Utility.PrimeNumberGenerator;

public class ParallelPrimeNumberGenerator extends Thread {

    private int l,r;
    private List<Integer> preGeneratedIds;
    private Set<Integer> ids;

    public ParallelPrimeNumberGenerator(int l, int r, Set<Integer> ids, List<Integer> preGeneratedIds) {
        this.l = l;
        this.r = r;
        this.ids = ids;
        this.preGeneratedIds = preGeneratedIds;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20000; i++) {
            int randomNumber = (int) (Math.random() * (r - l + 1)) + l;
            if (PrimeNumberGenerator.isPrim(randomNumber)) {
                continue;
            }
            if (preGeneratedIds.size() > 10 && ids.contains(randomNumber) && preGeneratedIds.contains(randomNumber)) {
                break;
            }

            preGeneratedIds.add(randomNumber);

        }
    }

}
