import java.util.ArrayList;
import java.util.Arrays;

public class Room {

    // find all of the maximum values for each row/value cell, with each path from cell to cell (from vault A to vault B)
    // find the maximum cell in row 8
    // find the path

    //Cell (money in cell, max sum, pathForMaxSum)

  public static int[][] exampleArray = {
            {1000,900,800,0,1000000, 0, 600, 1001},
            {1000, 900, 800, 0, 0, 0, 600, 1001},
            {1000 ,900, 800, 0, 0, 0, 600, 1001},
            {1000, 900, 800, 0, 0, 0, 600, 1001},
            {1000,900, 800, 0, 0, 0, 600, 1001},
            {1000,900, 800, 0, 0, 0, 600, 1001},
            {1000,900, 800, 0, 0, 0, 600, 1001},
            {1000,900, 800, 0, 0, 0, 600, 1001},};

  public static void print2DArray(int[][] anArray){
      for(int i = 0;i< anArray.length;i++){
          for(int j = 0; j<anArray.length;j++) {
              System.out.print(anArray[i][j] + " ");
          }
          System.out.println();

      }
  }
//  public static void printArrayList;

public static ArrayList<ArrayList<Cell>> makeRoom(int[][] array){
    ArrayList<ArrayList<Cell>> room = new ArrayList<>();
      int length = array.length;
      for(int i=0;i<length;i++){
          ArrayList<Cell> aRow = new ArrayList<>();
          for(int j=0;j<length;j++){
              // the default path is just the index/vault of the cell
              ArrayList<Integer> aPath = new ArrayList<>(Arrays.asList(j));
              int totalGems = array[i][j];;
              int initGems = array[i][j];
              // make a new cell with a basic path and gems corresponding to arrayd
              Cell aCell = new Cell(aPath,totalGems,initGems);
              aRow.add(aCell);
          }
          room.add(aRow);
      }
//      printRoom(room);
      return room;
}

  public static ArrayList<ArrayList<Cell>> findBestPaths(ArrayList<ArrayList<Cell>> room){
      // make new arrayList
      // size of both dimensions is the same, so just pick one for the array length
      int roomSize = room.get(0).size();
       int maxIndex = roomSize-1;
      // iterate thorough the rows (start on the second row from the bottom)
    for(int i = maxIndex-1;i>=0;i--){
        ArrayList<Cell> aRow = room.get(i); /// why does this dynamically change rows!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // iterate thorugh each column
        for(int j = 0;j<roomSize;j++){
            Cell aCell = aRow.get(j);
            // compare adding the left, middle and right
            // initialize left and right values to -1, incase the index is out of bounds
            // we use -1 so that the sum is guaranteed to be the minimum value
            int left = -1;
            int middle = -1;
            int right = -1;

            // check if the index is valid (index not too big or too small)
            if((j-1)>=0) {
                Cell leftCell = room.get(i + 1).get(j - 1);
                // add sum from beginning to the initial of the end
                left = getPathVal(leftCell.totalGems, aCell.initialGems, j - 1);
            }

            // we dont need to check the middle cell, since this will always be valid
            Cell middleCell = room.get(i+1).get(j);
            middle = getPathVal(middleCell.totalGems, aCell.initialGems, j);

            if((j)<roomSize - 1) {
                Cell rightCell = room.get(i + 1).get(j + 1);
               right = getPathVal(rightCell.totalGems, aCell.initialGems, j + 1);;
            }
            // compare the indices to find the largest sum (invalid indices will have sum of -1)
            int bestPathVal = Math.max(left,middle);
            bestPathVal = Math.max(bestPathVal,right);

            // next we have to use our bestPathVal to get our best path
            ArrayList<Integer> bestPath = new ArrayList<>();
            // to make your path, just append to the previous cell's path
            if(bestPathVal == left) {
                Cell leftCell = room.get(i + 1).get(j - 1);
                bestPath = leftCell.bestPath;
                bestPath.add(j);
            }
            else if(bestPathVal == middle){
                bestPath = middleCell.bestPath;
                bestPath.add(j);
            }
            else if(bestPathVal == right){
                Cell rightCell = room.get(i + 1).get(j + 1);
                bestPath = rightCell.bestPath;
                bestPath.add(j);
            }

            // update our Cell object
            aCell.totalGems = bestPathVal;
            aCell.bestPath = bestPath;
            //
        }
//        printRow(aRow,1);
    }
    printRoom(room);
    return room;
  }
//    public static ArrayList<Cell> getFirstRow(int[][] anArray){
//       ArrayList<Cell> aRow = new ArrayList<Cell>();
//       int size = anArray.length;
//       for(int i = 0; i<size;i++){
//
//           // initialize these cells
//           //path is just vault i to vault i
//           //total Gems and initialGems are the same value
//           Cell aCell = new Cell(new Path(i,i),anArray[size-1][i],anArray[size-1][i]);
//           aRow.add(aCell);
//       }
//        return aRow;
//    }

    public static int getPathVal(int beginVal,int endVal,int vault){
      int pathVal = -1;
       pathVal = beginVal + endVal;
      return pathVal;
    }

// helper functions
    public static void printRoom(ArrayList<ArrayList<Cell>> room){
      for (int i = 0;i<room.size();i++){
          printRow(room.get(i),1);
      }
    }
    public static void printRow(ArrayList<Cell> row, int type){
        switch(type){
            case 1:
                row.forEach((cell) -> System.out.print(cell.totalGems + " | "));
                System.out.println();
                break;
            case 2:
                row.forEach((cell) -> System.out.print(cell.initialGems + "  | "));
                System.out.println();
                break;
        }
    }





}
