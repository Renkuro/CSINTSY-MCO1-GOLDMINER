public class Miner {

    private Board board;
    private int x_position;
    private int y_position;
    private char direction;
    private String scannedObject;
    private int distance;

    public Miner(int x,int y) {
        x_position = x;
        y_position = y;
        direction = 'E';
        distance = 0;
        scannedObject = "NULL";
    }

    // Front direction scan action
    public void scan(){
        int x,y,n;
        boolean found = false;
        Tile[][] matrix;
        x = x_position;
        y = y_position;
        n = board.getN();
        matrix = board.getMatrix();

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
            if((x>=0 && x<n)&&(y>=0 && y<n)){
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

        System.out.println(scannedObject);
    }


    // 90-degree clockwise rotation action
    public void rotate() {
        if(direction=='E')
            direction='S';
        else if(direction=='S')
            direction='W';
        else if(direction=='W')
            direction='N';
        else
            direction='E';
    }

    // Current direction forward action
    public boolean forward(){
        int x,y,n;
        boolean valid = false;
        Tile[][] matrix;
        x = x_position;
        y = y_position;
        n = board.getN();
        matrix = board.getMatrix();

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

        if((x>=0 && x<n)&&(y>=0 && y<n)){
            matrix[x_position][y_position].setOccupied(false);
            matrix[x][y].setOccupied(true);
            setPosition(x,y);
            valid = true;
        }
        else
            System.out.println("Invalid movement");

        if(board.getGold().getisOccupied()==true)
            board.setIsEnd(true);
        else if(matrix[x][y] instanceof Pit){
            board.setGameOver(true);
            board.setIsEnd(true);
        }
        else if(matrix[x][y] instanceof Beacon){
            distance = ((Beacon)matrix[x][y]).computeDistance(board);
        }

        return valid;

    }

    public void setupBoard(Board board){
        this.board = board;
    }

    public int getX_position() {
        return x_position;
    }

    public int getY_position() {
        return y_position;
    }

    public char getDirection(){
        return direction;
    }

    public int getDistance(){
        return distance;
    }

    public String getScannedObject(){
        return scannedObject;
    }

    public void setPosition(int x_position, int y_position) {
        this.x_position = x_position;
        this.y_position = y_position;
    }
}