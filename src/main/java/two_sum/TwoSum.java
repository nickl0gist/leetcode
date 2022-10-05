package two_sum;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Created on 05.10.2022
 * @author Mykola Horkov
 * <br> mykola.horkov@gmail.com
 * <br>LeetCode:"Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * <br>You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * <br>You can return the answer in any order."
 * <br>Example 1:
 * <br>Input: nums = [2,7,11,15], target = 9
 * <br>Output: [0,1]
 * <br>Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 */
public class TwoSum {
    /**
     * Memory Usage: 43 MB, less than 86.33%
     * <br>Runtime: 786 ms, faster than 5.08%
     */
    public static int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        for (int i = 0; i < nums.length; i++) {
            int finalI = i; // store current i index in pseudo final ref for stream to work.
            int index = IntStream.range(finalI + 1, len) // each loop iteration we take part of initial nums array from next element from i till the end of the array.
                    .filter(j -> target - nums[finalI] == nums[j]) // filter stream to index of  element (j) which equals target minus current element.
                    .findFirst() // take first from filtered indexes
                    .orElse(-1); // return -1 if there is no indexes
            if(index > -1){
                return new int[]{finalI, index}; // return array which contains current i index from outer loop and filtered j index from stream.
            }
        }
        return new int[0];
    }

    /**
     * <br>Memory Usage: 42.3 MB, less than 93.97%
     * <br>Runtime: 1 ms, faster than 99.77%
     */
    public static int[] twoSumOptimized(int[] nums, int target) {
        //Map Keys for values of the nums array, Map Value keep indexes of the elements in nums array
        Map<Integer,Integer> resultMap = new HashMap<>();

        //loop through the elements of nums array
        for (int i = 0; i < nums.length; i++) {
            //if Map contains element which equals target minus current values of nums
            if(resultMap.containsKey(target - nums[i])){
                //return array of Map Value got by [target minus current array element] key and current index of array.
                return new int[] {resultMap.get(target - nums[i]), i};
            }
            //otherwise add new element to Map [current value of the array, current index of the loop]
            resultMap.put(nums[i], i);
        }
        return new int[0];
    }
}
