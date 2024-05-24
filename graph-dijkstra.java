import java.util.*;

public class Solution {
    
    public static class Edge {
        int v1;
        int v2;
        int w;
        public Edge(int v1,int v2,int w){
            this.v1 = v1;
            this.v2 = v2;
            this.w = w;
        }  
    }
    
    public static class Graph {
        public int numberOfVertex;
        List<Edge> edges;
        public Map<Integer, List<int[]>> graph;
        
        public Graph(int numberOfVertex, List<Edge> edges){
            this.numberOfVertex = numberOfVertex;
            this.edges = edges;
            this.graph = new HashMap<>();
            
            for(int i = 1; i<= numberOfVertex; i++)
                graph.put(i, new ArrayList<int[]>());

            for(Edge edge: edges){
                graph.get(edge.v1).add(new int[]{edge.v2, edge.w});
                graph.get(edge.v2).add(new int[]{edge.v1, edge.w});    
            }
        }
        
        public int[] diskastra(int src) { 
            Queue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);
            List<Integer> visited = new ArrayList();
            int[] distances = new int[this.numberOfVertex+1];            
            Arrays.fill(distances, Integer.MAX_VALUE);
            
            pq.add(new int[]{src,0});
            
            while(!pq.isEmpty()){
                int[] point = pq.poll();
                int vertex = point[0];
                int weight = point[1];
                
                if(visited.contains(vertex))
                    continue;
                visited.add(vertex);
                
                distances[vertex] = weight;
                
                List<int[]> adjVertexs = graph.get(vertex);
                for(int[] adjVertex : adjVertexs){
                    if(visited.contains(adjVertex[0]))
                        continue;

                    pq.add(new int[]{adjVertex[0], adjVertex[1]+weight});
                }
            }
                
            return result;
        }
        
    }


    public static void main(String[] args) {
        List<Edge> edges = new ArrayList();
        edges.add(new Edge(1,2,2));
        edges.add(new Edge(1,4,5));
        
        // edge.add(2,1,4);
        edges.add(new Edge(2,4,1));
        edges.add(new Edge(2,5,4));
        edges.add(new Edge(2,3,3));
        
        // edge.add(3,2,3);
        edges.add(new Edge(3,4,2));
        edges.add(new Edge(3,5,5));
        edges.add(new Edge(3,6,4));
        edges.add(new Edge(3,7,2));
        
        // edge.add(4,1,5);
        // edge.add(4,2,1);
        // edge.add(4,3,2);
        edges.add(new Edge(4,5,2));
        
        // edge.add(5,4,2);
        // edge.add(5,2,4);
        // edge.add(5,3,5);
        edges.add(new Edge(5,6,1));
        
        // edge.add(6,5,1);
        // edge.add(6,3,4);
        edges.add(new Edge(6,7,3));
        
        // edge.add(7,6,3);
        // edge.add(7,3,2);
        
        int numberOfVertices = 7;

        Graph graph = new Graph(numberOfVertices, edges);
        
        int[] results = graph.diskastra(1);
        int i = 0;
        for(int result: results){
            System.out.print(i + "-" + result + ", ");
            i++;
        }
    }
}
