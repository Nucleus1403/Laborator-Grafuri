package Matrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UndirectedGraphs
{
    public int Size;
    public static UndirectedGraphs Instance = null;
    public Matrix MatrixGrid;
    public Matrix AuxMatrixGrid;
    public int[] ValidateList;
    public int[] Coada;
    private Scanner sc = new Scanner(new File("C:\\Users\\claud\\IdeaProjects\\Laborator-Grafuri\\src\\Matrix\\Input"));

    public UndirectedGraphs() throws FileNotFoundException {

    }
    public static UndirectedGraphs GetInstance()
    {
        if(Instance==null) {
            try {
                Instance = new UndirectedGraphs();
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
        AuxMatrixGrid = new Matrix(Size+1);
        ValidateList = new int[Size+1];
        Coada = new int[Size+1];

    }

    public void ReadEdges()
    {
        int x,y;
        while (sc.hasNextLine())
        {
            x= sc.nextInt();
            y=sc.nextInt();
            MatrixGrid.Grid[x][y]=1;
            MatrixGrid.Grid[y][x]=1;

        }
        sc.close();
    }
    public void ShowMatrix()
    {
        for (int i = 0; i < Size; i++)
        {
            for (int j = 0; j < Size; j++)
            {
                System.out.print(MatrixGrid.Grid[i][j] +" ");
            }
            System.out.print("\n");

        }
    }
    public void ShowGrids()
    {
        for (int i = 0; i < Size; i++)
        {
            for (int j = 0; j < i; j++)
            {
                if(MatrixGrid.Grid[j][i]==1)
                {
                    System.out.println((j+1)+" "+(i+1));
                }
            }
        }
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
                MatrixGrid.Grid[j][i]=value;
            }
        }
    }
    public void StartDepthFirstSearch(int value)
    {
        for (int i = 0; i < Size; i++) {
            ValidateList[i]=0;
        }
        DepthFirstSearch(value,1,true,MatrixGrid);
        System.out.print("\n");
    }

    public void StartBreadthFirstSearch(int value)
    {
        for (int i = 0; i < Size; i++) {
            ValidateList[i]=0;
            Coada[i]=0;
        }
        BreadthFirstSearch(value);
    }

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

    public void ShowAllGraphs()
    {
        int count =1;
        if(CheckConex(0,false,true,MatrixGrid))
            return;

        for (int i = 0; i < Size; i++) {
            if(ValidateList[i]==0)
            {
                count++;
                System.out.print("\n");
                DepthFirstSearch(i,i+1,true,MatrixGrid);
            }
        }
        System.out.print("\n");
        System.out.print("Number of conex graphs : "+count);
    }

    public void GenerateConex()
    {
        if(CheckConex(0,false,false,MatrixGrid))
            return;

        for (int i = 0; i < Size; i++) {
            if(ValidateList[i]==0)
            {

                MatrixGrid.Grid[0][i]=1;
                MatrixGrid.Grid[i][0]=1;
                DepthFirstSearch(i,1,false,MatrixGrid);

            }
        }
        System.out.print("\n");
        System.out.println("Made Conex :");
        ShowMatrix();
        System.out.print("\n");

    }

    public void GenerateNonConex()
    {
        GenerateFakeMatrix();
        for (int i = 0; i < Size; i++) {
            DeleteNodeConnection(i);
            if(!CheckConex(0,false,false,AuxMatrixGrid))
            {
                System.out.println("If you delete the Node : "+ (i+1)+" you generate nonconex Graph");
                return;
            }
            GenerateFakeMatrix();
        }
    }


    private void DeleteNodeConnection(int node)
    {
        for (int i = 0; i < Size; i++) {

            AuxMatrixGrid.Grid[i][node]=0;
            AuxMatrixGrid.Grid[node][i]=0;
        }
    }

    private void GenerateFakeMatrix()
    {
        for (int i = 0; i < Size; i++) {
            for (int j = 0; j < Size; j++) {

                AuxMatrixGrid.Grid[i][j]=MatrixGrid.Grid[i][j];
            }

        }
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

}