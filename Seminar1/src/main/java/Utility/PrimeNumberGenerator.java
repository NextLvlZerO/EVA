package Utility;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumberGenerator {

    public static int getPrimeNumber(int l, int r) {
        for (int i = 0; i < 20000; i++) {
            int randomNumber = (int) (Math.random() * (r - l + 1)) + l;
            if (isPrim(randomNumber)) return randomNumber;
        }
        return -1;
    }

    public static boolean isPrim(int n) {
        if (n <= 1) return false;
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (n%i == 0) return false;
        }
        return true;
    }

    public List<Integer> getPrimeNumbers(int n, int l, int r) {
        List<Integer> primes = new ArrayList<Integer>();

        while (primes.size() < n) {
            int randomNumber = (int) (Math.random() * (r - l + 1)) + l;
            if (isPrim(randomNumber) && !primes.contains(randomNumber)) primes.add(randomNumber);
        }
        return primes;
    }
}
