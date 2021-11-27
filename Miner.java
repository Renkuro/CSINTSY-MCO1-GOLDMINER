public class Miner {

    private int x_position;
    private int y_position;
    private char direction;

    public Miner(int x,int y,char direction) {
        x_position = x;
        y_position = y;
        this.direction = direction;
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
}