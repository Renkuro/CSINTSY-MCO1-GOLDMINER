public class Miner {

    private int x_position;
    private int y_position;
    private char direction;

    Miner() {
        x_position = 1;
        y_position = 1;
        direction = 'E';
    }

    // Front direction scan action
    public void scan() {

    }

    // 90-degree clockwise rotation action
    public void rotate() {
        if(direction=='E')
            direction='S';
        else if(direction=='S')
            direction='W';
        else if(direction=='W')
            direction='N';
        else
            direction='E';
    }

    // Current direction forward action
    public void forward() {

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

    public void setPosition(int x_position, int y_position) {
        this.x_position = x_position;
        this.y_position = y_position;
    }
}