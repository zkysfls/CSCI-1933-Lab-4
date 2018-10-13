import java.util.Scanner;
public class HeatGrid
{
    int [][] Grid;
    String [][] sources = new String[100][100]; //set everything to ""
    //source[x][y] = src;
    //if(source[x][y]!="")
    int w,h;
    double decay;
    int heat;
    int distance;
    public HeatGrid(int height,int width)
    {
        w = width;
        h = height;
        int i,j;
        Grid = new int[height][width];
        for(i = 0; i < height; i++)
            for(j = 0; j < width; j++)
            {
                Grid[i][j] = 0;
                sources[i][j] = " ";
            }

    }
    public boolean placeSource(String src, int x, int y)
    {
        int l = 1;
        if( x > w || y > h || x < 0 || y < 0 || sources[x][y] != " ")
        {
            return false;
        }
        else if(src == "l")
        {
            Grid[x][y] += l;
            sources[x][y] = src;
            return true;
        }
        else if(src == "c")
        {
            sources[x][y] = src;
            placeheat(x, y, 4, 0.5, 2);
        }
        else if(src == "f")
        {
            sources[x][y] = src;
            placeheat(x, y, 10, 0.35, 4);
        }
        else if(src == "i")
        {
            sources[x][y] = src;
            placeheat(x, y, -2, 0.5, 1);
        }
        else if(src == "r")
        {
            sources[x][y] = src;
            placeheat(x, y, -8, 0.2, 3);
        }
        else if(src == "g")
        {
            sources[x][y] = src;
            placeheat(x, y, -20, 0.15, 5);
        }
        return true;

    }
    public void placeheat(int x, int y, int heat, double decay, int range)
    {
        int a, b = 0;
        int distance;
        for(a = 0; a < Grid.length; a++)
            for(b = 0; b < Grid[a].length; b++)
            {
                distance = compare(Math.abs(a - x),Math.abs(b - y));
                if(distance <= range)
                {
                    Grid[a][b] += decayHeat(decay,heat,distance);
                }
            }

    }
    public int[][] getHeats()
    {
        return Grid;
    }
    public  int getHeat(int x, int y)
    {
        return Grid[x][y];
    }
    public int getNetHeat()
    {
        int i, j;
        int heat = 0;
        for(i = 0; i < h; i++)
            for(j = 0; j < w; j++)
            {
                heat += getHeats()[i][j];
            }
        return heat;
    }
    public String toString()
    {
        String s = "";
        int i,j;
        for(i = 0; i < h; i++)
        {
            for (j = 0; j < w; j++) {
                s += getHeats()[i][j] + "  ";
            }
            s += "\n";
        }
        return s;
    }

    private int decayHeat(double decay, int heat, int distance)
    {
        return (int)(heat * Math.exp(-1 * decay * distance));
    }
    public int compare(int a, int b)
    {
        if(a >= b)
            return a;
        else
            return b;
    }
    public static void main(String[] args)
    {
        //String c = "c";
        int x = 3;
        int y = 3;
        int i,j;
        //Scanner s = new Scanner(System.in);
        //Scanner l = new Scanner(System.in);
        //int X = s.nextInt();
        //int Y = s.nextInt();
       // String L = l.nextLine();
        HeatGrid W = new HeatGrid(9,9);
        HeatGrid T = new HeatGrid(8,9);
       // W.placeSource("r",4,4)T.placeSource("c",2,3)T.placeSource("i",2,6);
        T.placeSource("r",7,8);
        T.placeSource("c", 2, 3);
        T.placeSource("i", 2, 6);
       // T.placeSource("i",2,6);
       // T.placeSource("r",7,8);
        //W.placeSource("c",3,2);
       // W.placeSource("g",1,1);
        //W.placeSource(c,x,y);
        //W.placeSource(L,X,Y);
        //System.out.println(W);
        System.out.println(T);
        //int H = h;
        //int W = w;

    }
    }
