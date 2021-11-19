public class Gold extends Tile{

    public Gold(int row, int column) {
        super(row, column);
    }


    @Override
    public String toString(){
        if(super.getisOccupied()==false)
            return "  G  ";
        else 
            return "  M  ";
    } 
}