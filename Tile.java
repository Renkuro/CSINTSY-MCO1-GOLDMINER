public class Tile {
    
    private int row;
    private int column;
    private boolean isOccupied;


    public Tile(int row, int column){  
        this.row = row;
        this.column = column;
        isOccupied = false;
    }
    
    public int getRow(){
        return this.row;
    }

    public int getColumn(){
        return this.column;
    }

    public boolean getisOccupied(){
        return isOccupied;
    }

    public void setOccupied(boolean b){
        isOccupied = b;
    }

    @Override
    public String toString(){
        if(isOccupied==false)
            return "  ~  ";
        else 
            return "  M  ";
    } 
}
