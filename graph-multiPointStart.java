import java.util.*;

public class Solution {

    public static int findMinDist(int m, int n, List<int[]> srcs) {
        Queue<int[]> queue = new ArrayDeque();
        Set<String> visited = new HashSet();
        int[][] directions = {{0,-1},{-1,0},{0,1},{1,0}};
        
        for(int[] point: srcs){
            queue.add(new int[]{point[0], point[1], 0});
        }
        
        int minDistance = -1;
        
        while(!queue.isEmpty()){
            int[] point = queue.poll();
            
            if(visited.contains(point[0] + "," + point[1]))
                continue;
            visited.add(point[0] + "," + point[1]);
            minDistance = point[2];
            
            for(int[] direction: directions){
                int[] step = new int[]{point[0]+direction[0], point[1]+direction[1],  point[2]+1};
                
                // out of limit
                if(step[0] < 0 || step[0] == m || step[1] < 0 || step[1] == n)
                    continue;
                
                // visited? - do not add if visited, visited addition can be handled on line #21.
                if(visited.contains(step[0] + "," + step[1]))
                    continue;
                
                queue.add(step);
            } 
        }
        return minDistance;
        
    }

    public static void main(String[] args) {
        int n = 7;
        int m = 7;
        
        List<int[]> src = new ArrayList();
        src.add(new int[]{1, 1});
        src.add(new int[]{0, 6});
        src.add(new int[]{5, 4});

        int result = findMinDist(n, m, src);
        System.out.println("Minimum Distance: " + result);
    }
}
