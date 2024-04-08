// Q: 540. Single Element in a Sorted Array

// You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.
// Return the single element that appears only once.
// Your solution must run in O(log n) time and O(1) space.

// Example 1:

// Input: nums = [1,1,2,3,3,4,4,8,8]
// Output: 2
// Example 2:

// Input: nums = [3,3,7,7,10,11,11]
// Output: 10

// Constraints:

// 1 <= nums.length <= 105
// 0 <= nums[i] <= 105

class Solution {
    public int singleNonDuplicate(int[] nums) {
        // 1,1,2,2,3,3,4,4,8
        // 0 1 2 3 4 5 6 7 8
        //         ^
        // 3,3,4,4,7,10,10

        // 1
        // 1 1 3

        return search(nums, 0, nums.length-1);
    }

    private int search(int[] nums, int start, int end) {
        int mid = start + (end - start)/2;

        if(nums.length == 1)
            return nums[0];

        if(nums.length == 2)
            return nums[0] == nums[1] ? -1 : nums[0];
        
        // mid +1 & -1 should not be same --> mid is ans
        if((mid-1 < 0 || nums[mid-1] != nums[mid]) 
            && (mid+1 > nums.length -1 || nums[mid] != nums[mid+1]))
            return nums[mid];

        // in left
        // even --> even + 1 same
        // no -> start, mid-2
        // odd --> odd - 1 same
        // no -> start, mid -1

        // in right 
        // even --> even + 1 same
        // yes -> mid+2, end
        // odd --> odd - 1 same
        // yes -> mid +1, end

        if(mid % 2 == 0) { // even
            if(nums[mid] == nums[mid+1])
                return search(nums, mid+2, end);
            else 
                return search(nums, start, mid-2);
        }
        else {
            if(nums[mid-1] == nums[mid]) // odd
                return search(nums, mid+1, end);
            else 
                return search(nums, start, mid-1);
        }        
    }
}
