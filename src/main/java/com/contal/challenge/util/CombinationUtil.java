package com.contal.challenge.util;

import java.util.ArrayList;
import java.util.List;

public class CombinationUtil {

    public static List<List<Integer>> combinationSum(List<Integer> arr, int sum) {

        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        findNumbers(ans, arr, sum, 0, temp);
        return ans;
    }

    private static void findNumbers(List<List<Integer>> ans,
                            List<Integer> arr, int sum, int index, List<Integer> temp) {
        if (sum == 0) {
            // Adding deep copy of list to ans
            ans.add(new ArrayList<>(temp));
            return;
        }

        for (int i = index; i < arr.size(); i++) {
            // checking that sum does not become negative
            if ((sum - arr.get(i)) >= 0) {
                // adding element which can contribute to
                // sum
                temp.add(arr.get(i));
                findNumbers(ans, arr, sum - arr.get(i), i, temp);

                // removing element from list (backtracking)
                temp.remove(arr.get(i));
            }
        }
    }
}
