package com.example.algorithmtest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.bind.ValidationEvent;

import sun.awt.SunHints;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.

 You may assume that each input would have exactly one solution, and you may not use the same element twice.
 */

public class Easy_TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0;i < nums.length;i ++) {
            map.put(nums[i],i);
        }
        Integer key = null;
        Integer integ = null;
        Iterator iter = map.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry entry = (Map.Entry)iter.next();
            // 获取key
            key = (Integer) entry.getKey();
            // 获取value
            integ = (Integer)entry.getValue();
            System.out.println("key: " + key + ", value: " + integ);
        }
        for(int i = 0;i < nums.length;i ++) {
            int y = target - nums[i];
            System.out.println("y: " + y + ", map.get(y): " + map.get(y));
            if(map.containsKey(y) && map.get(y) != i)
                return new int[]{i,map.get(y)};
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        int[] test = new int[]{2,1,2,2,2,6,7,11,2};
        int[] result = twoSum(test, 4);
        System.out.println(result[0] + ", " + result[1]);
    }
}
