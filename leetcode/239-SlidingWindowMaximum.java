class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 0 1 2 3 4 5 6 7 8 9
        // 5 3 2 4 3 2 1 0 1 2

        // [1,3,-1,-3,5,3,6,7]
        //  0 1  2  3 4 5 6 7

        // [1]
        //  0

        // nums = [7,2,4]
        // k = 2

        // [1,3,1,2,0,5]
        //  0 1 2 3 4 5
        // k = 3

        // challenge: when same value (solution: line #36 '=' in condition '>=')
        // [-7,-8,7,5,7,1,6,0]
        //   0  1 2 3 4 5 6 7

        ArrayList<Integer> list = new ArrayList();
        Deque<Integer> dq = new ArrayDeque();

        for(int i=0; i < nums.length; i++){

            if (dq.size() > 0 && dq.peekFirst() <= i - k) // out of k window
                dq.removeFirst();

            while (dq.size() > 0 && nums[dq.peekLast()] < nums[i])
                dq.removeLast();

            if (dq.size() == 0 || nums[dq.peekLast()] >= nums[i])
                dq.addLast(i);

            if (i >= k-1) // pick when widow moves // i >= 3
                list.add(nums[dq.peekFirst()]);
        }

        return list.stream().mapToInt(v -> v).toArray();
    }

    
    public int[] maxSlidingWindow2Part(int[] nums, int k) {
    
        ArrayList<Integer> list = new ArrayList();
        Deque<Integer> dq = new ArrayDeque();

        // first window 
        int i = 0;
        while(i < k){
            if(dq.size() == 0){
                dq.addLast(i);
                i ++;
                continue;
            }

            while (dq.size() > 0 && nums[dq.peekLast()] < nums[i])
                dq.removeLast();

            dq.addLast(i);
            i ++;
        }
        list.add(nums[dq.peekFirst()]);

        // rest of the windows
        while(i < nums.length){

            if (dq.size() > 0 && dq.peekFirst() <= i - k) // out of k window
                dq.removeFirst();

            while (dq.size() > 0 && nums[dq.peekLast()] < nums[i])
                dq.removeLast();

            dq.addLast(i);
            i++;

            list.add(nums[dq.peekFirst()]);
        }

        return list.stream().mapToInt(v -> v).toArray();
    }

}
