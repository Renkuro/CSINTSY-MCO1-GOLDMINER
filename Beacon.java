import java.lang.Math;

public class Beacon extends Tile{

    public Beacon(int x, int y){  
        super(x,y);
    }

    public int computeDistance(Board board){
        return (Math.abs(super.getX()-board.getGold().getX()) + Math.abs(super.getY()-board.getGold().getY()));
    }
    
    @Override
    public String toString(){
        if(super.getisOccupied()==false)
            return "  B  ";
        else 
            return "  M  ";
    } 
}