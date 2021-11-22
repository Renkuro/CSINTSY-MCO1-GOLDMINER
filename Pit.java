public class Pit extends Tile{

    public Pit(int x, int y){  
        super(x, y);
    }

    @Override
    public String toString(){
        if(super.getisOccupied()==false)
            return "  P  ";
        else 
            return "  M  ";
    } 
}