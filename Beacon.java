import java.util.Scanner;

public class Beacon {

    private int row;
    private int column;


    public Beacon(){  
        
    }

    public static Beacon[] beaconInitialize(int n) {
        Beacon[] beacons = new Beacon[n];
        for( int i=0 ; i<n; i++){
            Scanner sc = new Scanner (System.in);
            System.out.print("\nEnter beacon row: ");
            int row = sc.nextInt();
            System.out.print("Enter beacon column: ");
            int col = sc.nextInt();
            Beacon beaconTemp = new Beacon();
            beaconTemp.setPos(row,col);
            beacons[i]= beaconTemp;
        }
        return beacons;
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

    public static int beaconCompute(int n){
        double y;
        y = n * 0.1;
        if ((int)y<1)
            return 1;
        else 
            return (int)y;
    }
}