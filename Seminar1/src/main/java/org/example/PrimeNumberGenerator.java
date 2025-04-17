package org.example;

public class PrimeNumberGenerator {
    public int getPrimeNumber(int l, int r) {
        while (l < r) {
            if (isPrim(l)) return l;
            l++;
        }
        return -1;
    }


    public boolean isPrim(int n) {
        if (n <= 1) return false;
        for (int i = 2; i < n; i++) {
            if (n%i == 0) return false;
        }
        return true;
    }
}
