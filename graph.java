// "static void main" must be defined in a public class.
// "static void main" must be defined in a public class.

class Edge {
	int v1;
	int v2;
    Edge (int _v1, int _v2){
        v1=_v1;
        v2=_v2;
    }
}
public class Main {
    static List<Integer> visited = new ArrayList<Integer>();
    public static void main(String[] args) {
        System.out.println("Hello World!");
        List<Edge> edges = new ArrayList();
        edges.add(new Edge(1,2));
        edges.add(new Edge(2,3));
        edges.add(new Edge(3,4));
        edges.add(new Edge(1,4));
        edges.add(new Edge(5,7));
        edges.add(new Edge(6,8));
        edges.add(new Edge(9,7));
        edges.add(new Edge(6,9));
        edges.add(new Edge(7,12));
        edges.add(new Edge(12,13));
        edges.add(new Edge(15,16));
        edges.add(new Edge(6,7));
        edges.add(new Edge(3,17));
        edges.add(new Edge(2,4));
        
        Map<Integer, List<Integer>> graph = createGraph(edges,17);
        
        for(Map.Entry<Integer, List<Integer>> entry: graph.entrySet()){
            System.out.print(entry.getKey() + " > ");
            for(Integer vertex: entry.getValue()){
                System.out.print(vertex + "  ");
            }
            System.out.println();
        }
        
        // dfs
        // bfs
        for(Map.Entry<Integer, List<Integer>> entry : graph.entrySet()){
            int key = entry.getKey();
            if(visited.contains(key))
                continue;
            bfs(graph, key);
        }
        
        for(Integer value: visited){
            System.out.print(value + "  ");
        }
        System.out.println();
        
        
        Set<int[]> obstacles = new HashSet<>();

        int[] array1 = {2, 2};
        int[] array2 = {3, 2};
        int[] array3 = {4, 1};

        obstacles.add(array1);
        obstacles.add(array2);
        obstacles.add(array3);
        
        System.out.print("hi");
        
        int minDistance = findMinDist(5,5,new int[]{0,1}, new int[]{4,3}, obstacles);
        System.out.println("ans  --  " + minDistance);
        
    }
    
    
    public static Map<Integer, List<Integer>> createGraph(List<Edge> edges, int size){
        Map<Integer, List<Integer>> graph = new HashMap();
        
        for(int i = 1; i <= size; i++){
            graph.put(i, new ArrayList());
        }
        
        for(Edge edge: edges){
            if(!graph.containsKey(edge.v1)){
                List<Integer> list = new ArrayList();
                list.add(edge.v2);
                graph.put(edge.v1, list);
            } 
            else{
                graph.get(edge.v1).add(edge.v2);
            }
            
            if(!graph.containsKey(edge.v2)){
                List<Integer> list = new ArrayList();
                list.add(edge.v1);
                graph.put(edge.v2, list);
            } 
            else{
                graph.get(edge.v2).add(edge.v1);
            }   
        }
        
        return graph;
    }
    
    public static void dfs(Map<Integer, List<Integer>> graph, int src){
        if(visited.contains(src))
            return;
            
        visited.add(src);
        
        List<Integer> list = graph.get(src);
        for(Integer vertex: list){
            dfs(graph, vertex);
        }
    }
    
    public static void bfs(Map<Integer, List<Integer>> graph, int src){
        
        Queue<Integer> queue = new ArrayDeque<Integer>();
        queue.add(src);
        
        while(!queue.isEmpty()){
            Integer startVertex = queue.poll();
            if(visited.contains(startVertex))
                continue;
            
            visited.add(startVertex);
            
            List<Integer> adjVertices = graph.get(startVertex);
            
            for(Integer vertex: adjVertices){
                if(visited.contains(vertex))
                    continue;
                
                queue.add(vertex);
            }
        }
        
    }
    
    // imp point to learn
    // - consider visited item
    // - consider returning ditance vs breaking loop option.
    // conclusion: dry run is very important
    public static int findMinDist(int m, int n, int[] src, int[] dest, Set<int[]> obstacles) { 
        Queue<int[]> queue = new ArrayDeque();
        List<int[]> visited = new ArrayList();
        queue.add(new int[]{src[0], src[1], 0});
        visited.add(new int[]{src[0], src[1]});
        
        int[][] directions = {{1,0},{0,1},{-1,0},{0,-1}};
        int minDistance = -1;
                
        while(!queue.isEmpty()){
            
            int[] point = queue.poll();
            System.out.println("hi "  + point[0] +" "+point[1]+" "+point[2]);
            
            // distance found here
            minDistance = point[2];
            for(int[] direction: directions){
                int[] newPoint = {point[0] + direction[0], point[1] + direction[1], point[2] + 1};
                // visited? --> continue
                if(visited.contains(new int[]{newPoint[0], newPoint[1]}))
                    continue;
                   
                // point is out of grid limit --> continue
                if(newPoint[0] < 0 || newPoint[0] == m || newPoint[1] < 0 || newPoint[1] == n)
                    continue;
                
                // contains in obstacles? --> continue
                if(obstacles.contains(new int[]{newPoint[0], newPoint[1]}))
                    continue;
                
                // found --> break
                if(dest[0] == newPoint[0] && dest[1] == newPoint[1])
                    // break; // problem: it will just exit current for loop but not outer while loop, due to which it will keep on pulling and adding items in 
                    return minDistance;
                   
                // add to queue
                queue.add(newPoint);
                visited.add(new int[]{newPoint[0], newPoint[1]});
            }
        }
        
        return minDistance;
    }
}

// 1  2  4  3  4  3  17  17  5  7  9  12  6  6  13  8  8  10  11  14  15  16  
// 1  2  4  3  17  5  7  9  12  6  13  8  10  11  14  15  16
