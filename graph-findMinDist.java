import java.util.*;

public class Solution {

    public static int findMinDist(int n, int m, int[] src, int[] dest, Set<int[]> obstacles) {
        Set<String> visited = new HashSet<>();
        Queue<int[]> points = new ArrayDeque<>();
        Set<String> obstacleSet = new HashSet<>();

        // Convert obstacles to string representations
        for (int[] obstacle : obstacles) {
            obstacleSet.add(obstacle[0] + "," + obstacle[1]);
        }

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        points.add(new int[]{src[0], src[1], 0});

        while (!points.isEmpty()) {
            int[] point = points.poll();
            String pointKey = point[0] + "," + point[1];

            if (visited.contains(pointKey)) continue;
            visited.add(pointKey);

            if (dest[0] == point[0] && dest[1] == point[1]) return point[2];

            for (int[] direction : directions) {
                int newX = point[0] + direction[0];
                int newY = point[1] + direction[1];
                int newDist = point[2] + 1;
                String newPointKey = newX + "," + newY;

                if (newX >= 0 && newX < n && newY >= 0 && newY < m &&
                    !visited.contains(newPointKey) && !obstacleSet.contains(newPointKey)) {
                    points.add(new int[]{newX, newY, newDist});
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int n = 5;
        int m = 5;
        int[] src = {0, 0};
        int[] dest = {4, 4};
        Set<int[]> obstacles = new HashSet<>();
        obstacles.add(new int[]{2, 2});
        obstacles.add(new int[]{3, 3});

        int result = findMinDist(n, m, src, dest, obstacles);
        System.out.println("Minimum Distance: " + result);
    }
}
