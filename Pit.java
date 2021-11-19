public class Pit extends Tile{

    public Pit(int row, int column){  
        super(row, column);
    }

    @Override
    public String toString(){
        if(super.getisOccupied()==false)
            return "  P  ";
        else 
            return "  M  ";
    } 
}