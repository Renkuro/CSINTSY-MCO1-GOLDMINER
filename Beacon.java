public class Beacon extends Tile{

    public Beacon(int row, int column){  
        super(row,column);
    }

    @Override
    public String toString(){
        if(super.getisOccupied()==false)
            return "  B  ";
        else 
            return "  M  ";
    } 
}