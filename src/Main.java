public class Main {

    public static void main(String[] args) {
        UndirectedGraphs graphs = UndirectedGraphs.GetInstance();
        graphs.Initialization();
        graphs.ReadMatrixGrid();
        graphs.ShowMatrix();
        graphs.ShowGrids();
        graphs.StartDepthFirstSearch(0);
    }
}
