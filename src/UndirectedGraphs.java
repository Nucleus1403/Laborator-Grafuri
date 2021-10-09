import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UndirectedGraphs
{
    public int Size;
    public static UndirectedGraphs Instance = null;
    public Matrix MatrixGrid;
    public int[] ValidateList;
    private Scanner sc = new Scanner(new File("C:\\Users\\denis\\IdeaProjects\\Laborator-Grafuri\\src\\Input"));

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
        ValidateList = new int[Size+1];
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
        DepthFirstSearch(value);
    }

    public void DepthFirstSearch(int value)
    {
        ValidateList[value]=1;
        System.out.print((value+1)+" ");

        for(int i=1;i<=Size;i++)
            if(MatrixGrid.Grid[value][i]==1 && ValidateList[i]==0)
            {
                DepthFirstSearch(i);
            }
    }

    int bfs(int start)
    {
        int i,k,st,dr;
        st=dr=1;
        x[1]=start;
        v[start]=1;//vizitez varful initial
        while(st<=dr)//cat timp coada nu este vida
        {
            k=x[st];//preiau un element din coada
            for(i=1;i<=n;i++)//parcurg varfurile
                if(v[i]==0 && a[k][i]==1)//daca i este vecin cu k si nu este vizitat
                {
                    v[i]=1;//il vizitez
                    x[++dr]=i;//il adaug in coada
                }
            st++;//sterg din coada
        }
        return dr;//returnam numarul de varfuri vizitate
    }

}
