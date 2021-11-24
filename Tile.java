public class Tile implements Cloneable{
    
    private int x;
    private int y;
    private boolean isOccupied;


    public Tile(int x, int y){  
        this.x = x;
        this.y = y;
        isOccupied = false;
    }

    public Tile (Tile tile){
        this.x = tile.x;
        this.y = tile.y;
        isOccupied = false;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
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

    @Override
    public Object clone() {
    try {
        return (Tile) super.clone();
    } catch (CloneNotSupportedException e) {
        return new Tile(this.x,this.y);
    }
}

}
