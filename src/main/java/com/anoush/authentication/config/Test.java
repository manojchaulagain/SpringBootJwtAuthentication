package com.anoush.authentication.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

  public static void main(String[] args) {

    int values[] = {-1, 0, 1, 2, -1, -4};
    threeSum(values);
  }

  public static List<List<Integer>> threeSum(int[] nums) {
    Map<Integer, Integer> itemCount = new HashMap<>();
    List<List<Integer>> finalList = new ArrayList<>();

    for (int i : nums) {
      if (itemCount.containsKey(i)) {
        itemCount.put(i, itemCount.get(i) + 1);
      } else {
        itemCount.put(i, 1);
      }
    }

    for (int i : nums) {

      itemCount.forEach(
          (key, val) -> {
            int temp = -(key + i);
            boolean duped = false;
            if (itemCount.containsKey(temp)) {
              List<Integer> list = new ArrayList<>();
              list.add(i);
              if (list.contains(key) && itemCount.get(key) > 1) {
                duped = true;
                list.add(key);
              } else {
                list.add(key);
              }
              if (list.contains(key)) {
                if (duped && itemCount.get(temp) > 2) {
                  list.add(temp);
                } else if (itemCount.get(temp) > 1) {
                  list.add(temp);
                }
              } else {
                list.add(temp);
              }
              if (list.size() == 3 && !finalList.contains(list)) {
                finalList.add(list);
              }
            }
          });
    }
    return finalList;
  }
}
