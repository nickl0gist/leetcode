package median_of_two_sorted_arrays;

/**
 * Created on 06.10.2022
 * @author Mykola Horkov
 * <br> mykola.horkov@gmail.com
 * <p>
 * <br> Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 * <br> The overall run time complexity should be O(log (m+n)).
 * <p>
 * Example 1:
 * <p>
 * <br> Input: nums1 = [1,3], nums2 = [2]
 * <br> Output: 2.00000
 * <br> Explanation: merged array = [1,2,3] and median is 2.
 * <p>
 * Example 2:
 * <p>
 * <br> Input: nums1 = [1,2], nums2 = [3,4]
 * <br> Output: 2.50000
 * <br> Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 */
public class MedianOfTwoSortedArrays {

    /**
     * Solution by merging two arrays in a way to keep merged array sorted.
     */
    public static double findMedianSortedArraysWithMerging(int[] nums1, int[] nums2) {
        //length of each initial array kept in variables
        int lenNum1 = nums1.length;
        int lenNum2 = nums2.length;
        int[] mergedArray;

        // mergedArray(...) works whe the first array is greater than the other one.
        // That's why if nums2 is longer than nums1, mergedArray() has to be called in opposite way.
        if (lenNum1 >= lenNum2){
            mergedArray = mergeArrays(nums1, lenNum1, nums2, lenNum2);
        } else {
            mergedArray = mergeArrays(nums2, lenNum2, nums1, lenNum1);
        }
        // keep Length of merged array in a variable.
        int mergedArrayLength = lenNum1 + lenNum2;

        // if mergedArrayLength is odd
        if(mergedArrayLength % 2 != 0){
            return mergedArray[mergedArrayLength / 2] * 1.0;
        } else { // if mergedArrayLength is even
            return (mergedArray[mergedArrayLength / 2] + mergedArray[mergedArrayLength / 2 - 1]) / 2.0 ;
        }
    }

    /**
     * Merging of two arrays. Constraint: biggerArr array has to be longer than smallerArr.
     */
    private static int[] mergeArrays(int[] biggerArr, int lenNum1, int[] smallerArr, int lenNum2){
        int[] mergedArray = new int[lenNum1 + lenNum2];
        // pointer to current position in mergedArray
        int counter = 0;
        // pointer to current element which was placed into merged array from smaller array.
        int smallerArrCounter = 0;

        // outer loop through elements in bigger Array
        for (int k : biggerArr) {
            // inner loop through smaller array. Begins from +1 position of last added element from smallest array.
            for (int i = smallerArrCounter; i < lenNum2; i++) {
                // if element from smallest array is less or equal to element in bigger array.
                if (smallerArr[i] <= k) {
                    // add element from smallest array to merged array. And Increase counter
                    mergedArray[counter++] = smallerArr[i];
                    // Increase smallerArrCounter
                    smallerArrCounter++;
                }
            }
            // Ones all elements from smallest array which are less than current element in bigger array,
            // add current biggerArray element to merged array and increase counter
            mergedArray[counter++] = k;
        }

        // if there are elements in smaller array which were not added yet.
        while(smallerArrCounter < lenNum2){
            mergedArray[counter++] = smallerArr[smallerArrCounter++];
        }
        return mergedArray;
    }

    /**
     * Binary search of Median. Complexity is O(log(min(m,n)).
     * The initial arrays divided into two parts. Left side has last element which represents Maximum in that part, and most
     * left element in the right part is Minimum element in that part.
     * nums1 would be divided to LeftX and RightX part, nums2 into LeftY and RightY.
     * The aim to achieve condition when
     * MaxLeftY <= MinRightX and MaxLeftX <= MinRightY.
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2){
        //length of each initial array kept in variables
        int m = nums1.length;
        int n = nums2.length;

        // in case when size of the nums1 array is bigger than nums2 call method itself with swapped arrays.
        // ensuring that binary search happens on minimum size array, which considered as left part of merged array.
        if(m > n)
            return findMedianSortedArrays(nums2, nums1);

        // keep total length of both arrays.
        int totalLength = m + n;

        // defining quantity of elements in left side of merged array which contains elements from
        // left part of array nums1 and left part of nums2 array. The last element in this Left part of merged array
        // is Median in case of odd merged array, or the minimum value in case of even merged array
        int half = (totalLength + 1) / 2;

        // define pointers to the most left index of the nums1 array and to the index right after last in nums1
        int low = 0;
        int high = m;

        // while left pointer is less or equals to right pointer
        while(low <= high){
            //                                    low         cutOfNums1=3                 high
            // define middle of left part (nums1) [m0, m1, m2,  |   m3, m4, m5, m6]  7
            int cutOfNums1 = (high - low) / 2 + low;
            //                                                                 cutOfNums2=5
            // define middle of the right array  (nums2) [n0, n1, n2, n3, n4    |    n5, n6, n7, n8]
            int cutOfNums2 = half-cutOfNums1;
            // so now left side of merged array has 8 elements and right side has 8 elements
            // [m0, m1, m2, n0, n1, n2, n3, n4 | m3, m4, m5, m6, n5, n6, n7, n8]

            // if cutOfNums1 is less than size of left part(nums1)
            // and compare minimum value of right side of nums1(m3) with maximum of left side of nums2 (n4)
            // in case when minRightM is less than maxLeftOfN it means that we are too far to the left of nums1
            // and we need to move low to cutOfNums1 + 1
            //               cutOfNums1=3        low         high
            // [m0, m1, m2,    | m3,        m4, m5, m6]  7
            if(cutOfNums1 < m && nums1[cutOfNums1] < nums2[cutOfNums2-1]){   //
                low = cutOfNums1+1; // change low bound to cutOfNums1+1
            }
            // if cutOfNums1 is still after first index (cutOfNums1 > 0)
            // and maximum of left side of nums1 (m2) is greater than minimum of right side of n (n5)
            // so we are to far to right in nums1 and we need to move high index to cut-1 value
            // low     high
            // [m0, m1, m2, m3, m4, m5, m6]
            else if(cutOfNums1 > 0 && nums1[cutOfNums1-1]>nums2[cutOfNums2]){
                high = cutOfNums1-1;
            }
            // if none of above conditions are the case, so we reached condition when
            // MaxLeftY <= MinRightX and MaxLeftX <= MinRightY.
            else{
                int left = 0;
                // in case when all elements of array nums1 are in right side of merged array
                // and all elements of nums2 array are in left side.
                if(cutOfNums1 == 0){
                    // in this case 'left' equals to the most right element of nums2
                    left = nums2[cutOfNums2-1];
                }
                // The opposite case nums2 are in right side, nums1 in left side.
                else if(cutOfNums2 == 0){
                    // 'left' equal to the most right element of nums1
                    left = nums1[cutOfNums1-1];
                }
                // otherwise the maximum between right elements in left parts in split of nums1 and split nums2
                else{
                    left = Math.max(nums1[cutOfNums1-1], nums2[cutOfNums2-1]);
                }
                // if total length of merged array is odd, return 'left'
                if(totalLength % 2 == 1) return left;

                // nex goes in case if total length of merged array is even
                int right = 0;

                // The case when all items of nums are in the left side and nums2 are all at the right side.
                // So 'right' is a most left element in nums2
                if(cutOfNums1 == m){
                    right = nums2[cutOfNums2];
                }
                // The opposite case
                else if(cutOfNums2 == n){
                    right = nums1[cutOfNums1];
                }
                // Otherwise the 'right' element is a minimum between left elements in right parts of split of nums1 and split nums2
                else{
                    right = Math.min(nums1[cutOfNums1], nums2[cutOfNums2]);
                }
                // THe result is a average of 'left' and 'right'
                return (left+right)/2.0;
            }
        }
        return 0;
    }
}
