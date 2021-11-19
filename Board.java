import java.util.Scanner;

import javax.sql.rowset.CachedRowSet;

public class Board {

    private Tile[][] matrix;
    private Miner miner;
    private int n;
    private boolean isEnd;
    
    public Board(int n){

        matrix = new Tile[n][n];
        miner = new Miner();
        isEnd = false;
        this.n = n;

        initializeBeacon();
        initializePit();
        initializeGold();
        initializeBoard();
        initializeMiner();
    }

//initializing

    private void initializeBeacon(){
        double y;
        boolean valid;
        Scanner sc = new Scanner (System.in);
        int row,col;

        y = n * 0.1;
        if ((int)y<1)
            y = 1;

        for( int i=0 ; i< (int)y; i++){
            valid = false;
            while(valid == false){
                System.out.print("\nEnter beacon row: ");
                row = sc.nextInt();
                System.out.print("Enter beacon column: ");
                col = sc.nextInt();
                if(matrix[row-1][col-1]==null){
                    matrix[row-1][col-1]= new Beacon(row-1,col-1);
                    valid = true;
                    System.out.println("Input valid");
                }
                else
                    System.out.println("Input invalid");
            }
        }
       
    }

    private void initializePit(){
        double y;
        Scanner sc = new Scanner (System.in);
        int row, col;
        boolean valid;

        y = n * 0.25;
        if ((int)y<1)
            y = 1;

        for( int i=0 ; i<(int)y; i++){
            valid = false;
            while(valid == false){
                System.out.print("\nEnter pit row: ");
                row = sc.nextInt();
                System.out.print("Enter pit column: ");
                col = sc.nextInt();
                if(matrix[row-1][col-1]==null){
                    matrix[row-1][col-1]= new Pit(row-1,col-1);
                    valid = true;
                    System.out.println("Input valid");
                }
                else
                    System.out.println("Input invalid");
            }
        }
        
    }

    private void initializeGold(){
        Scanner sc = new Scanner (System.in);
        int row, col;
        boolean valid;

        System.out.print("\nEnter gold row: ");
        row = sc.nextInt();
        System.out.print("Enter gold column: ");
        col = sc.nextInt();
        valid = false;

        while(valid == false){
            if(matrix[row-1][col-1]==null){
                matrix[row-1][col-1]= new Gold(row-1,col-1);
                valid = true;
                System.out.println("Input valid");
            }
            else
                System.out.println("Input invalid");
        }
    }

    private void initializeBoard(){
        System.out.println(miner.getDirection());
        for(int i=0;i<getN();i++){
            for(int j=0;j<getN();j++){
                if(matrix[i][j]==null)
                    matrix[i][j] = new Tile(i,j);
            }
        }
        miner.rotate();
        System.out.println(miner.getDirection());
        miner.rotate();
        System.out.println(miner.getDirection());
        miner.rotate();
        System.out.println(miner.getDirection());
        miner.rotate();
        System.out.println(miner.getDirection());
    }

    private void initializeMiner(){
        matrix[0][0].setOccupied(true);
    }

//getters and setters

    public boolean getIsEnd(){
        return isEnd;
    }

    public void setIsEnd(boolean b){
        this.isEnd = b;
    }

    public int getN(){
        return n;
    }
    
    public void printBoard(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

//miner movement

    public void scan(){
        char direction = miner.getDirection();

        switch(direction){
            case 'N' :
            case 'E' :
            case 'S' :
            case 'W' :
            default: break;
        }



    }

    public void forward(){

    }

    public static void main (String args[]){

        System.out.print("Enter board size: ");
        Scanner sc = new Scanner(System.in);
        int boardsize = sc.nextInt();
        Board board = new Board(boardsize);
/*
        while(!board.getIsEnd()){

        }
*/  
        board.printBoard();

        sc.close();
    }
}
