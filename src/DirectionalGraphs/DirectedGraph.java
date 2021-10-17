package DirectionalGraphs;

import Matrix.Matrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DirectedGraph
{
    public int Size;
    public static DirectedGraph Instance = null;
    public Matrix MatrixGrid;

    private Scanner sc = new Scanner(new File("C:\\Users\\claud\\IdeaProjects\\Laborator-Grafuri\\src\\DirectionalGraphs\\Input"));

    public DirectedGraph() throws FileNotFoundException {

    }
    public static DirectedGraph GetInstance()
    {
        if(Instance==null) {
            try {
                Instance = new DirectedGraph();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return Instance;
    }

    public void Initialization()
    {
        Size = sc.nextInt();
        MatrixGrid = new Matrix(Size+1);
    }

    public void ReadMatrixGrid()
    {
        int value;
        for (int i = 0; i < Size; i++)
        {
            for (int j = 0; j < Size; j++)
            {
                value = sc.nextInt();
                MatrixGrid.Grid[i][j]=value;
            }
        }
    }

    final static int MAXValue = 99999;

    public void FloydWarshall()
    {
        int distance[][] = new int[Size][Size];

        for (int i = 0; i < Size; i++)
            for (int j = 0; j < Size; j++)
                distance[i][j] = MatrixGrid.Grid[i][j];

        for (int k = 0; k < Size; k++)
        {
            for (int i = 0; i < Size; i++)
            {
                for (int j = 0; j < Size; j++)
                {
                    if (distance[i][k] + distance[k][j] < distance[i][j])
                        distance[i][j] = distance[i][k] + distance[k][j];
                }
            }
        }
        Solution(distance);
    }

    private void Solution(int dinstance[][])
    {
        System.out.println("The following matrix shows the shortest "+
                "distances between every pair of vertices");

        for (int i=0; i<Size; ++i)
        {
            for (int j=0; j<Size; ++j)
            {
                if (dinstance[i][j]>=2000)
                    System.out.print("INF ");
                else
                    System.out.print(dinstance[i][j]+"   ");
            }
            System.out.println();
        }
    }

}
