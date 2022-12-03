import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> maxPath = new ArrayList<>(Arrays.asList(-1,-1));
        int[][] test = {{1,2,3}
                    ,{4,5,6}
                    ,{7,8,9}};

        ArrayList<ArrayList<Cell>> room =  Room.makeRoom(Room.exampleArray);
        Room.findBestPaths(room);
//        GaussJordan.printRoom(room);

    }



}

