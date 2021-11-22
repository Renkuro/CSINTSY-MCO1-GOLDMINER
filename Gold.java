public class Gold extends Tile{

    public Gold(int x, int y) {
        super(x, y);
    }


    @Override
    public String toString(){
        if(super.getisOccupied()==false)
            return "  G  ";
        else 
            return "  M  ";
    } 
}