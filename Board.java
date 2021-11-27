import java.util.*;

public class Board implements Comparable<Board>{

    private Board parent;
    private ArrayList<Board> children = new ArrayList<>();
    private Tile[][] matrix;
    private Miner miner;
    private boolean end=false, gameover=false;
    private Gold gold;
    private int n;
    private int heuristics = 0;

    private Scanner sc = new Scanner (System.in);

    public Board(int n){

        matrix = new Tile[n][n];
        this.n = n;
        this.miner = new Miner(0,0,'E');
        matrix[0][0] = new Tile(0,0);
        matrix[0][0].setOccupied(true);

        initializeBeacon();
        initializePit();
        initializeGold();
        initializeBoard();
    }

    public Board(int x, int y, char direction, int n, int heuristics, Board parent){

        
        this.parent = parent;
        this.heuristics = heuristics;

        this.matrix = new Tile[n][n];
        this.gold = parent.getGold();
        this.n = n;

        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix.length;j++){
                if((parent.getMatrix())[i][j] instanceof Pit)
                    this.matrix[i][j] = new Pit(((Pit)(parent.getMatrix())[i][j]));
                else if((parent.getMatrix())[i][j] instanceof Beacon)
                    this.matrix[i][j] = new Beacon(((Beacon)(parent.getMatrix())[i][j]));
                else if((parent.getMatrix())[i][j] instanceof Gold)
                    this.matrix[i][j] = new Gold(((Gold)(parent.getMatrix())[i][j]));
                else
                    this.matrix[i][j] = new Tile((parent.getMatrix())[i][j]);
                if(i==x && j==y)
                    matrix[i][j].setOccupied(true);
            }
        }
        
        if(this.matrix[x][y] instanceof Gold)
            this.end = true;
        else if(this.matrix[x][y] instanceof Pit)
            this.gameover = true;
        
        miner = new Miner(x,y,direction);
    }

    public void generateChildren(Board child){
        children.add(child);
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
                else
                    System.out.println("Input invalid");
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
                    if(matrix[row-1][col-1]==null && minerSurround(row-1, col-1)==false){
                        matrix[row-1][col-1]= new Pit(row-1,col-1);
                        valid = true;
                        System.out.println("Input valid");
                    }
                    else
                        System.out.println("Input invalid");
                }
                else
                    System.out.println("Input invalid");
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
            else
                System.out.println("Input invalid");
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

    private boolean pitCheck(int row, int col){
        if(row+1>=n || matrix[row+1][col] instanceof Pit)
            if(row-1<0 || matrix[row-1][col] instanceof Pit)
                if(col+1>=n || matrix[row][col+1] instanceof Pit)
                    if(col-1<0 || matrix[row][col-1] instanceof Pit)
                        return true;
        return false;
    }

    private boolean minerSurround(int row, int col){
        if(row==1 && col == 0){
            if(matrix[0][1] instanceof Pit)
                return true;
        }
        else if(row==0 && col==1){
            if(matrix[1][0] instanceof Pit)
                return true;
        }
        return false;
   
    }
//getters and setters

    public Gold getGold(){
        return gold;
    }

    public int getN(){
        return n;
    }
    
    public Tile[][] getMatrix(){
        return matrix;
    }

    public boolean getEnd(){
        return end;
    }

    public boolean getGameOver(){
        return gameover;
    }

    public ArrayList<Board> getChildren(){
        if(children==null)
            return null;
        return children;
    }

    public Board getChildrenIndex(int index){
        if(children.get(index)==null)
            return null;
        return children.get(index);
    }
    
    public int getX(){
        return miner.getX_position();
    }

    public int getY(){
        return miner.getY_position();
    }

    public char getDirection(){
        return miner.getDirection();
    }

    public Board getParent(){
        return parent;
    }

    public void printBoard(){   
        System.out.println("miner direction: " + miner.getDirection());
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                System.out.print(this.matrix[i][j]);
            }
            System.out.println();
        }
    }

    public int getHeuristics(){
        return heuristics;
    }

    @Override
    public int compareTo(Board board){
        if(this.getHeuristics() < board.getHeuristics())
            return -1;
        else if(this.getHeuristics() > board.getHeuristics())
            return 1;
        return 0;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + getX();
        result = prime * result + getY();
        result = prime * result + (int)getDirection();
        return result;
    }

    @Override
    public String toString(){
        return "Heuristic value is: " + this.getHeuristics();
    }

    @Override
    public boolean equals(Object o){
        if((this.miner.getDirection()==((Board)o).miner.getDirection()) && (this.miner.getX_position() == ((Board)o).miner.getX_position()) && (this.miner.getY_position() == ((Board)o).miner.getY_position()))
            return true;
        else
            return false;
    }

    public static void main (String args[]){
        
        Board board;
        Queue<Board> queue = new LinkedList<Board>();
        PriorityQueue<Board> priorityQueue = new PriorityQueue<Board>();
        HashSet<Board> memory = new HashSet<>();
        boolean state = false;
        String search="";

        Scanner sc = new Scanner(System.in);
        int boardsize=0;
        boolean valid=false;
        Board temp = null;
        char direction;

        ArrayList<Board> path = new ArrayList<Board>();
        ArrayList<Board> solution = new ArrayList<Board>();

        while(valid==false){
            System.out.print("Enter board size (8-64): ");
            boardsize = sc.nextInt();
            if(boardsize>=8 && boardsize<=64)
                valid = true;
            else
                System.out.println("Invalid input");
        }

        board = new Board(boardsize);
        
        sc.nextLine();
        valid = false;
        while(valid==false){
            System.out.print("\nEnter the level of rational behavior you want to use (R/S): ");
            search = sc.nextLine();
            if(search.equalsIgnoreCase("R") || search.equalsIgnoreCase("S"))
                valid = true;
            else
                System.out.println("Invalid input");
        }

        if(search.equalsIgnoreCase("R")){
            queue.add(board);
            while(state!=true){
                temp = queue.poll();
                path.add(temp);
                temp.printBoard();

                if(temp.getEnd()==true){
                    state = true;
                    break;
                }
                
                direction = temp.getDirection();

                if(temp.getGameOver()!=true){

                    //forward
                    int x,y;

                    x = temp.getX();
                    y = temp.getY();
                    
                    switch(direction){
                        case 'N' : x--;
                                break;
                        case 'E' : y++;
                                break;
                        case 'S' : x++;
                                break;
                        case 'W' : y--;
                                break;
                        default: break;
                    }

                    if((x>=0 && x<boardsize)&&(y>=0 && y<boardsize))
                        temp.generateChildren(new Board(x,y,direction,boardsize,0,temp));

                    //rotate
                    if(direction=='E')
                        direction='S';
                    else if(direction=='S')
                        direction='W';
                    else if(direction=='W')
                        direction='N';
                    else
                        direction='E';
                    temp.generateChildren(new Board(temp.getX(),temp.getY(),direction,boardsize,0,temp));
                    

                    //add children to queue
                    for(int i=0;i<temp.getChildren().size();i++){
                        queue.add(temp.getChildrenIndex(i));
                    }
                }
            }
        }
        else if(search.equalsIgnoreCase("S")){
            priorityQueue.add(board);
            int beaconPriority=0;
            while(state!=true){
                temp = priorityQueue.poll();
                memory.add(temp);
                path.add(temp);
                temp.printBoard();

                if(temp.getEnd()==true){
                    state = true;
                    break;
                }
                
                direction = temp.getDirection();

                if(temp.getGameOver()!=true){
                    
                    int x,y;
                    boolean found = false;
                    String scannedObject = "NULL";
                    Heuristic heuristic = new Heuristic();
                    Tile[][] matrix;
                    x = temp.getX();
                    y = temp.getY();
                    matrix = temp.getMatrix();

                    //scan
                    while(!found){
                        switch(direction){
                            case 'N' : x--;
                                    break;
                            case 'E' : y++;
                                    break;
                            case 'S' : x++;
                                    break;
                            case 'W' : y--;
                                    break;
                            default: break;
                        }
                        if((x>=0 && x<boardsize)&&(y>=0 && y<boardsize)){
                            if(matrix[x][y] instanceof Pit){
                                scannedObject = "P";
                                found=true;
                            }
                            else if(matrix[x][y] instanceof Gold){
                                scannedObject = "G";
                                found=true;
                            }
                            else if(matrix[x][y] instanceof Beacon){
                                scannedObject = "B";
                                found=true;
                            }
                        }
                        else{
                            scannedObject = "NULL"; break;
                        }
                    }

                    //forward
                    x = temp.getX();
                    y = temp.getY();

                    if(matrix[x][y] instanceof Beacon){
                        if(((Beacon)matrix[x][y]).computeDistance(board)<=boardsize)
                            beaconPriority = 1;
                    }
                    
                    switch(direction){
                        case 'N' : x--;
                                break;
                        case 'E' : y++;
                                break;
                        case 'S' : x++;
                                break;
                        case 'W' : y--;
                                break;
                        default: break;
                    }

                    if((x>=0 && x<boardsize)&&(y>=0 && y<boardsize))
                        temp.generateChildren(new Board(x,y,direction,boardsize,heuristic.forwardHeuristic(scannedObject,beaconPriority),temp));

                    //rotate
                    if(direction=='E')
                        direction='S';
                    else if(direction=='S')
                        direction='W';
                    else if(direction=='W')
                        direction='N';
                    else
                        direction='E';
                    temp.generateChildren(new Board(temp.getX(),temp.getY(),direction,boardsize,heuristic.rotateHeuristic(scannedObject,beaconPriority),temp));
                    

                    //add children to queue
                    for(int i=0;i<temp.getChildren().size();i++){
                        if(!memory.contains(temp.getChildrenIndex(i)) && !priorityQueue.contains(temp.getChildrenIndex(i)))
                            priorityQueue.add(temp.getChildrenIndex(i));
                    }
                }
            }
        }

        System.out.println("SUCCESS");
        System.out.println("Path is: ");
        while(temp.getParent()!=null){
            solution.add(temp);
            temp = temp.getParent();
        }
        solution.add(temp);
        Collections.reverse(solution);
        for(int i=0;i<solution.size();i++){
            solution.get(i).printBoard();
        }
        System.out.println(path.size());

      sc.close();
    }
}
