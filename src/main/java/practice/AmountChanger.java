package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AmountChanger {

    private static int nWays(int amount, List<Integer> denom, int startIndex, List<Integer> acc) {
        if (amount == 0) {
            System.out.println(acc);
            return 1;
        }
        if (amount < 0 || denom.size() == 0 || startIndex >= denom.size()) {
            return 0;
        }
        Integer first = denom.get(startIndex);
        if (first > amount) {
            return 0;
        }
        return nWays(amount - first, denom, startIndex, newAcc(acc, first)) + nWays(amount, denom, startIndex + 1, acc);
    }

    private static List<Integer> newAcc(List<Integer> acc, Integer first) {
        ArrayList<Integer> na = new ArrayList<>(acc);
        boolean added = na.add(first);
        assert added;
        return na;
    }

    public static void main(String[] args) {
        System.out.println(nWays(10, Arrays.asList(1, 2, 5, 10), 0, new ArrayList<>()));
    }
}
