class Solution {
    public boolean isMajorityElement(int[] nums, int target) {
        int N = nums.length;
        int leftIndex = search(nums, target, 0, N-1, "left");
        int rightIndex = search(nums, target, 0, N-1, "right");
                
        if(rightIndex == -1 && leftIndex == -1) // when target not found 
            return false;
        
        if((rightIndex - leftIndex + 1) > (nums.length)/2)
            return true;

        return false;
    }
    public int search(int[] nums, int target, int start, int end, String direction) {
        if(start>end)
            return -1;
        
        int mid = start + (end - start)/2;
        
        if(nums[mid] == target){
            int index;
            
            if(direction == "left")
                index = search(nums, target, start, mid-1, direction);
            else
                index = search(nums, target, mid+1, end, direction);
                
            if(index == -1)
                return mid;
            return index;
        }
        
        if(nums[mid] < target)
            return search(nums, target, mid+1, end, direction);
        else
            return search(nums, target, start, mid-1, direction);
        
    }
}
// [2,4,5,5,5,5,5,6,6]
//  0 1 2 3 4 5 6 7 8

// [10,101,101,101]
// 
// false

// [2,5,5] 5
//  0 1 2
// 
