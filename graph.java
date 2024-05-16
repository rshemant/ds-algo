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
        for(Map.Entry<Integer, List<Integer>> entry : graph.entrySet()){
            int key = entry.getKey();
            if(visited.contains(key))
                continue;
            dfs(graph, key);
        }
        
        for(Integer value: visited){
            System.out.print(value + "  ");
        }
        // bfs
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
}
