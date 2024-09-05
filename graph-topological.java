// "static void main" must be defined in a public class.
class Edge {
    int v1, v2;
    Edge(int _v1, int _v2) {
        v1 = _v1;
        v2 = _v2;
    }
}
public class Main {
    public static void main(String[] args) {
        List < Edge > edges = new ArrayList();
        edges.add(new Edge(1, 2));
        edges.add(new Edge(1, 3));
        edges.add(new Edge(3, 12));
        edges.add(new Edge(3, 4));
        edges.add(new Edge(2, 4));
        edges.add(new Edge(2, 5));
        edges.add(new Edge(4, 12));
        edges.add(new Edge(4, 5));
        edges.add(new Edge(6, 7));
        edges.add(new Edge(6, 8));
        edges.add(new Edge(7, 5));
        edges.add(new Edge(8, 10));
        edges.add(new Edge(8, 9));
        edges.add(new Edge(13, 8));
        edges.add(new Edge(13, 9));
        edges.add(new Edge(1, 3));
        edges.add(new Edge(10, 5));
        edges.add(new Edge(10, 11));
        int numOfVertex = 13;
        topological(edges, numOfVertex);
    }
    public static void topological(List < Edge > edges, int numOfVertex) {
        Map < Integer, List < Integer >> graph = new HashMap();
        for (int i = 1; i < numOfVertex + 1; i++) {
            graph.put(i, new ArrayList());
        }
        for (Edge edge: edges) {
            List < Integer > list = graph.get(edge.v1);
            list.add(edge.v2);
            graph.put(edge.v1, list);
        }
        Set < Integer > visited = new HashSet();
        Stack < Integer > stack = new Stack();
        for (Map.Entry < Integer, List < Integer >> entry: graph.entrySet()) {
            if (visited.contains(entry.getKey()))
                continue;
            dfs(graph, visited, stack, entry.getKey());
        }
        while (!stack.isEmpty())
            System.out.print(stack.pop() + "  ");
    }

    public static void dfs(
        Map < Integer, List < Integer >> graph,
        Set < Integer > visited,
        Stack < Integer > stack,
        Integer vertex
    ) {
        List < Integer > list = graph.get(vertex);

        for (Integer adjVertex: list) {
            if (visited.contains(adjVertex))
                continue;
            dfs(graph, visited, stack, adjVertex);
        }
        stack.push(vertex);
        visited.add(vertex);
    }
}
