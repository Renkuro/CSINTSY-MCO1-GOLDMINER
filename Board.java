import java.util.Scanner;

public class Board {

    private Scanner sc = new Scanner (System.in);
    private Tile[][] matrix;
    private Miner miner;
    private int n;
    private boolean isEnd, gameOver;
    private Gold gold;

    public Board(int n){

        matrix = new Tile[n][n];
        isEnd = false;
        gameOver = false;
        this.n = n;

        initializeBeacon();
        initializePit();
        initializeGold();
        initializeBoard();
    }

//initializing

    private void initializeBeacon(){
        double y;
        boolean valid;
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
                if(row<=n && row>=1 && col<=n && col>=1){
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
    }

    private void initializePit(){
        double y;
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
                if(row<=n && row>=1 && col<=n && col>=1){
                    if(matrix[row-1][col-1]==null && beaconCheck(row-1,col-1)==false){
                        matrix[row-1][col-1]= new Pit(row-1,col-1);
                        valid = true;
                        System.out.println("Input valid");
                    }
                    else
                        System.out.println("Input invalid");
                }
            }
        }
        
    }

    private void initializeGold(){
        int row, col;
        boolean valid;

        valid = false;

        while(valid == false){
            System.out.print("\nEnter gold row: ");
            row = sc.nextInt();
            System.out.print("Enter gold column: ");
            col = sc.nextInt();
            if(row<=n && row>=1 && col<=n && col>=1){
                if(matrix[row-1][col-1]==null && pitCheck(row-1,col-1)==false){
                    matrix[row-1][col-1]= new Gold(row-1,col-1);
                    valid = true;
                    this.gold = (Gold) matrix[row-1][col-1];
                    System.out.println("Input valid");
                }
                else
                    System.out.println("Input invalid");
            }
        }
    }

    private void initializeBoard(){
        for(int i=0;i<getN();i++){
            for(int j=0;j<getN();j++){
                if(matrix[i][j]==null)
                    matrix[i][j] = new Tile(i,j);
            }
        }
    }

    public void setupMiner(Miner miner){
        this.miner = miner;
        matrix[miner.getX_position()][miner.getY_position()].setOccupied(true);
    }

    private boolean beaconCheck(int row, int col){
        if((row+1)<n){
            if(matrix[row+1][col] instanceof Beacon)
                return true;
        }
        if((row-1)>=0){
            if(matrix[row-1][col] instanceof Beacon)
                return true;
        }
        if((col+1<n)){
            if(matrix[row][col+1] instanceof Beacon)
                return true;
        }
        if((col-1)>0){
            if(matrix[row][col-1] instanceof Beacon)
                return true;
        }
        return false;
    }

    private boolean pitCheck(int row, int col){
        if((row+1)<n){
            if(matrix[row+1][col] instanceof Pit)
                return true;
        }
        if((row-1)>=0){
            if(matrix[row-1][col] instanceof Pit)
                return true;
        }
        if((col+1<n)){
            if(matrix[row][col+1] instanceof Pit)
                return true;
        }
        if((col-1)>0){
            if(matrix[row][col-1] instanceof Pit)
                return true;
        }
        return false;
    }

//getters and setters

    public boolean getIsEnd(){
        return isEnd;
    }

    public boolean getGameOver(){
        return gameOver;
    }
    
    public void setIsEnd(boolean b){
        this.isEnd = b;
    }

    public void setGameOver(boolean b){
        this.gameOver = b;
    }

    public Gold getGold(){
        return gold;
    }

    public int getN(){
        return n;
    }
    
    public Tile[][] getMatrix(){
        return matrix;
    }

    public void printBoard(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static void main (String args[]){

        Scanner sc = new Scanner(System.in);
        int boardsize=0;
        boolean valid=false;

        while(valid==false){
            System.out.print("Enter board size (8-64): ");
            boardsize = sc.nextInt();
            if(boardsize>=8 && boardsize<=64)
                valid = true;
            else
                System.out.println("Invalid input");
        }
        Board board = new Board(boardsize);
        Miner miner = new Miner(0,0);
        board.setupMiner(miner);
        miner.setupBoard(board);
/*
        while(!board.getIsEnd()){

        }
*/ 
        
        board.printBoard();
        System.out.println("=-=-==-=-=-=-=-=-=-=-=-=-");
        miner.scan();
        miner.forward();
        board.printBoard();
        System.out.println("=-=-==-=-=-=-=-=-=-=-=-=-");
        System.out.println("isEnd = " + board.isEnd);
        System.out.println("gameOver = " + board.gameOver);
        System.out.println("distance = " + miner.getDistance());
        sc.close();
    }
}
