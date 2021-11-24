public class Miner {

    private int x_position;
    private int y_position;
    private char direction;
    private String scannedObject;
    private int distance;

    public Miner(int x,int y,char direction) {
        x_position = x;
        y_position = y;
        this.direction = direction;
        distance = 0;
        scannedObject = "NULL";
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

    public int getDistance(){
        return distance;
    }

    public String getScannedObject(){
        return scannedObject;
    }

    public void setPosition(int x_position, int y_position) {
        this.x_position = x_position;
        this.y_position = y_position;
    }
}