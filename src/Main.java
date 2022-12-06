import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
// Q1 test
        float[][] test = Matrix.test;
        float[][] A1 =Matrix.A1;
//        Matrix.print2DArray(A1);
        System.out.println();
//        Matrix.ForwardElimination(A1);
// Q2 test
        float[][] A2 = Matrix.A2;
        Matrix.print2DArray(A2);
        System.out.println();
        System.out.println();
//        Matrix.BetterBetterForwardElimination(A2);
        // Q3 test
        float[][] A3 = Matrix.A3;
        Matrix.GaussJordenElimination(A3);

        System.out.println();
        float s = 3.5f;
        int a = Math.round(s);
        System.out.println(a);

// Question 4
//        Room.question4(Room.assignmentArray);

    }



}

