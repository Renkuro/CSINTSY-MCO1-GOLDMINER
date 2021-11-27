public class Heuristic {
    
    public Heuristic(){

    }

    public int forwardHeuristic(String scannedObject,int beaconPriority){

        if(scannedObject.equals("P")) return 9 - beaconPriority;
        else if(scannedObject.equals("B")) return 6 - beaconPriority;
        else if (scannedObject.equals("G")) return 0;
        else return 7 - beaconPriority;
    }
    
    public int rotateHeuristic(String scannedObject,int beaconPriority){

        if(scannedObject.equals("P")) return 7 - beaconPriority;
        else if(scannedObject.equals("B")) return 7 - beaconPriority;
        else if (scannedObject.equals("G")) return 10;
        else return 7 - beaconPriority;
    }
}
