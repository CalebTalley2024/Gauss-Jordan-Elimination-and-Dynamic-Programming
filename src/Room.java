import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Room {

    // find all of the maximum values for each row/value cell, with each path from cell to cell (from vault A to vault B)
    // find the maximum cell in row 8
    // find the path

    //Cell (money in cell, max sum, pathForMaxSum)
    int[][] test = {{1,2,3}
            ,{4,5,6}
            ,{7,8,9}};

    public static int[][] exampleArray = {
            {1000,900,800,0,1000000, 0, 600, 1001},
            {1000, 900, 800, 0, 0, 0, 600, 1001},
            {1000 ,900, 800, 0, 0, 0, 600, 1001},
            {1000, 900, 800, 0, 0, 0, 600, 1001},
            {1000,900, 800, 0, 0, 0, 600, 1001},
            {1000,900, 800, 0, 0, 0, 600, 1001},
            {1000,900, 800, 0, 0, 0, 600, 1001},
            {1000,900, 800, 0, 0, 0, 600, 1001},};

    public static int[][] assignmentArray = {
            {96, 33, 44, 98, 75, 68, 99, 84},
            {10, 41, 1, 86, 46, 24, 53, 93},
            {83, 97, 94, 27, 65, 51, 30,7},
            {56, 70, 47, 64, 22, 88, 67, 12},
            {91, 11, 77, 48, 13, 71, 92, 15},
            {32, 59 ,17 ,25 ,31 ,4 ,16, 63},
            {79, 5, 14, 23, 78, 37, 40, 74},
            {35, 89, 52, 66, 82, 20, 95, 21},
    };


    // question4: brings everything together
    public static void question4(int[][] twoDArray){
        ArrayList<ArrayList<Cell>> room =  Room.makeRoom(twoDArray);
        ArrayList<ArrayList<Cell>> sortedRoom = Room.findBestPaths(room);
//      printRoom(sortedRoom); // TA: if you want to see what the room looks like, use this
        Room.roomOutput(sortedRoom);
    }
    // print2DArrayprints int[][]

//  public static void printArrayList;

    // make room full of cells from int[][]
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

    // find all of the best paths in room
    public static ArrayList<ArrayList<Cell>> findBestPaths(ArrayList<ArrayList<Cell>> room){
        // make new arrayList
        // size of both dimensions is the same, so just pick one for the array length
        int roomSize = room.get(0).size();
        int maxIndex = roomSize-1;
        // iterate thorough the rows (start on the second row from the bottom)
        for(int i = maxIndex-1;i>=0;i--){
            ArrayList<Cell> aRow = room.get(i); /// this is a reference to the actual room, if you want to just use the value, use clone()
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
                    left = getPathVal(leftCell.totalGems, aCell.initialGems);
                }

                // we dont need to check the middle cell, since this will always be valid
                Cell middleCell = room.get(i+1).get(j);
                middle = getPathVal(middleCell.totalGems, aCell.initialGems);

                if((j)<roomSize - 1) {
                    Cell rightCell = room.get(i + 1).get(j + 1);
                    right = getPathVal(rightCell.totalGems, aCell.initialGems);;
                }
                // compare the indices to find the largest sum (invalid indices will have sum of -1)
                int bestPathVal = Math.max(left,middle);
                bestPathVal = Math.max(bestPathVal,right);

                // next we have to use our bestPathVal to get our best path
                ArrayList<Integer> bestPath = new ArrayList<>();
                // to make your path, just append to the previous cell's path
                if(bestPathVal == left) {
                    Cell leftCell = room.get(i + 1).get(j - 1);
                    bestPath = (ArrayList<Integer>) leftCell.bestPath.clone(); // we use clone here since we just want the value, NOT the reference
                    bestPath.add(j);
                }
                else if(bestPathVal == middle){
                    bestPath = (ArrayList<Integer>) middleCell.bestPath.clone();
                    bestPath.add(j);
                }
                else if(bestPathVal == right){
                    Cell rightCell = room.get(i + 1).get(j + 1);
                    bestPath = (ArrayList<Integer>) rightCell.bestPath.clone();
                    bestPath.add(j);
                }
                // update our Cell object
                aCell.totalGems = bestPathVal;
                aCell.bestPath = bestPath;
                //
            }
//        printRow(aRow,1);
        }
//    printRoom(room);
        return room;
    }

    // hw output
    public static void roomOutput(ArrayList<ArrayList<Cell>> sortedRoom){
        // we know that the cell with the largest amount of money will be in the top row (index 0)
        ArrayList<Cell> topRow = sortedRoom.get(0);
        Cell cellMax = getMaxCell(topRow);

        int startSquare = cellMax.bestPath.get(0);

        ArrayList<Integer> bestPath = cellMax.bestPath;
        // remember to increase everything in bestPath by one (vaults are not zero based)
        int numGems = cellMax.totalGems;
        int pathMaxIndex = bestPath.size()-1;
        int endSquare = cellMax.bestPath.get(pathMaxIndex);

        System.out.println("(a) Bilbo's starting square: Row 8, Vault " + (startSquare + 1));

        System.out.print("(b) a representation of his path:");
        printPath(bestPath);

        System.out.println("(c) the total number of gems collected on the way: "+numGems );
        // we add 1 here to take into account 0-based
        System.out.println("(d) the number of the vault wherein the King has secreted the Arkenstone: Vault "+ (endSquare+1));



    }

    // helper functions
    // print the room
    public static void printRoom(ArrayList<ArrayList<Cell>> room){
        for (int i = 0;i<room.size();i++){
            printRow(room.get(i),1);
        }
    }
    // print a row of the room
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
    // get the sum of gems when taking path of length 2
    public static int getPathVal(int beginVal,int endVal){
        int pathVal = -1;
        pathVal = beginVal + endVal;
        return pathVal;
    }
    // finding the cell with the most gems
    public static Cell getMaxCell(ArrayList<Cell> rowOfVault ){
        Cell maxCell = new Cell();
        // set up variables for the max index and the best totalGem value
        int maxCellIndex = -1;
        int maxGemValue = 0;
        for(int i =0;i<rowOfVault.size();i++){
            int numGems = rowOfVault.get(i).totalGems;
            // if a cell has more gems than all of the others, set this cell and return it
            if(numGems> maxGemValue){
                maxGemValue = rowOfVault.get(i).totalGems;
                maxCellIndex = i;
            }
        }
        maxCell = rowOfVault.get(maxCellIndex);
        return maxCell;
    }
    // printing a path
    public static void printPath(ArrayList<Integer> path){
        String pathWritten = "";
        path.forEach((vNum)-> System.out.print("Vault " + (vNum + 1) + ", "));
//        path.forEach((vNum)-> System.out.print( vNum + " , " ));
        System.out.println();
    }



}
