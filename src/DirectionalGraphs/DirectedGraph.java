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

    /*
    public boolean CheckConex(int value,boolean showAnswer,boolean show,Matrix currentMatrix)
    {
        for (int i = 0; i < Size; i++) {
            ValidateList[i]=0;
        }
        DepthFirstSearch(value,1,show,currentMatrix);
        for (int i = 0; i < Size; i++) {
            if(ValidateList[i]==0)
            {
                if(showAnswer)
                    System.out.println("Not Conex");
                return false;
            }
        }
        if(showAnswer)
            System.out.println("Conex");

        return true;
    }






    public int GetNodeCount(int value,boolean show)
    {
        value-=1;
        int count =0;
        for (int i = 0; i < Size; i++) {
            if(MatrixGrid.Grid[value][i]==1)
                count++;
        }

        if(show)
            System.out.println("Count for node "+(value+1)+" is "+count);
        return count;
    }


    public void DepthFirstSearch(int value,int key,boolean show,Matrix currentMatrix)
    {
        ValidateList[value]=key;
        if(show)
            System.out.print((value+1)+" ");

        for(int i=1;i<=Size;i++)
            if(currentMatrix.Grid[value][i]==1 && ValidateList[i]==0)
            {
                DepthFirstSearch(i,key,show,currentMatrix);
            }
    }

    private int BreadthFirstSearch(int start)
    {
        int k,Left,Right;
        Left=Right=1;

        Coada[1]=start;
        ValidateList[start]=1;

        while(Left<=Right)
        {
            k=Coada[Left];
            for(int i=1;i<=Size;i++)
                if(ValidateList[i]==0 && MatrixGrid.Grid[k][i]==1)
                {
                    ValidateList[i]=1;
                    Coada[++Right]=i;
                }
            Left++;
        }
        return Right;
    }
*/
}
