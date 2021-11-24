public class Gold extends Tile{

    public Gold(int x, int y) {
        super(x, y);
    }

    public Gold (Gold tile){
        super(tile.getX(),tile.getY());
    }


    @Override
    public String toString(){
        if(super.getisOccupied()==false)
            return "  G  ";
        else 
            return "  M  ";
    } 
}