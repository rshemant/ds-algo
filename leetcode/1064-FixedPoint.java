// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        int res = fixedPoint(new int[]{0,1,2,3,4,5});
        System.out.println("out --> "+res);
    }
    public static int fixedPoint(int[] arr){
        return search(arr, 0, arr.length-1);
    }
    private static int search(int[] arr, int start, int end){
        if(start > end)
            return -1;
        
        int mid = start + (end - start)/2;
        
        if(mid == arr[mid]){
            int index = search(arr, start, mid-1);
            if(index == -1)
                return mid;
            return index;
        }
        
        if(mid > arr[mid])
            return search(arr, mid+1, end);
        else 
            return search(arr, start, mid-1);
    }
    
}
