public class Game {
    
    public Game(){
        System.out.print("Enter board size: ");
        Scanner sc = new Scanner(System.in);
        int boardsize = sc.nextInt();
        int beaconCount = Beacon.beaconCompute(boardsize);
        int pitCount = Pit.pitCompute(boardsize);

        System.out.print("* Beacon Initialization *\n");
        Beacon[] beacons = Beacon.beaconInitialize(beaconCount);

        System.out.print("* Pit Initialization *\n");
        Pit[] pits = Pit.pitInitialize(pitCount);
        
    }
}