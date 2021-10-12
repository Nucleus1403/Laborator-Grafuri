package DirectionalGraphs;

public class Main {
    public static void main(String[] args) {
        DirectedGraph graphs = DirectedGraph.GetInstance();
        graphs.Initialization();
        graphs.ReadMatrixGrid();
        graphs.FloydWarshall();
    }
}
