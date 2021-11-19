import java.util.Scanner;

public class Pit{

    private int row;
    private int column;


    public Pit(){  
    }

    public static Pit[] pitInitialize(int n) {
        Pit[] pits = new Pit[n];
        for( int i=0 ; i<n; i++){
            Scanner sc = new Scanner (System.in);
            System.out.print("\nEnter pit row: ");
            int row = sc.nextInt();
            System.out.print("Enter pit column: ");
            int col = sc.nextInt();
            Pit pitTemp = new Pit();
            pitTemp.setPos(row,col);
            pits[i]= pitTemp;
        }
        return pits;
    }

    public void setPos(int row, int column){
        this.row = row;
        this.column = column;
    }

    public int getRow(){
        return this.row;
    }

    public int getColumn(){
        return this.column;
    }

    public static int pitCompute(int n){
        double y;
        y = n * 0.25;
        if ((int)y<1)
            return 1;
        else 
            return (int)y;
    }
}