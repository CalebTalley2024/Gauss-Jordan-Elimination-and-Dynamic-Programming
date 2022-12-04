import java.util.ArrayList;
import java.util.Arrays;

public class Cell {

//    Path maxPath = new Path(-1,-1);
    //maxPath - array that has 1-8 numbers
    ArrayList<Integer> bestPath = new ArrayList<>(Arrays.asList(-1,-1));
    int totalGems = 0;
    int initialGems = 0;

    Cell(ArrayList<Integer> maxPath,int totalGems,int initialGems){
        this.bestPath = maxPath;
        this.totalGems = totalGems;
        this.initialGems = initialGems;
    }
    Cell(){
        ArrayList<Integer> bestPath = new ArrayList<>(Arrays.asList(-1,-1));
        totalGems = 0;
        initialGems = 0;
    }
}
