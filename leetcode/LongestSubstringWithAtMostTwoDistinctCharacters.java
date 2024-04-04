// Longest Substring with At Most Two Distinct Characters
public class Main {
    public static void main(String[] args) {
        int result = lengthOfLongestSubstringTwoDistinct("ccaabbqwerwweeeere cvvcc");
        System.out.println(result);
        
    }
    public static int lengthOfLongestSubstringTwoDistinct(String s){
        // s = "e c e b a"
        //      0 1 2 3 4
        
        // ccaabbb
        // 0123456
        Map<Character, Integer> map = new HashMap<>();
        
        int p1 = 0, p2 = 0;
        int distChar = 0;
        final int window = 2;
        
        int max = 0;
        
        while(p2 < s.length()){
 
            char c2 = s.charAt(p2);
            
            if(!map.containsKey(c2)){                
                map.put(c2, 1);
                distChar ++; // we are only increasing this when we actually find distinct value. and not we doing increment.
            } else {
                map.put(c2, map.get(c2) + 1);
            }   
            
            // why while not if? : to make sure dist count is in limit, & that happens only when char is removed from hashmap
            while(distChar > window){
                
                // remove item
                // why? : to move p1 ahead
                // why p1 ahead?
                // which? : p1
                
                // challenge: how to reduce distChar and when?
                // reduce distChar only when we remore item from hashmap, this will make sure correct distinct char count
                char c1 = s.charAt(p1);
                
                if(map.get(c1) > 1)
                    map.put(c1, map.get(c1) - 1);
                else {
                    map.remove(c1);
                    distChar --;
                }
                p1 ++;
            }
            
            max = Math.max(max, p2-p1);
            
            p2 ++;
            
        }
        
        return max +1; // +1 to get actual length because java pointers are strting from 0 to length-1
    }
}
