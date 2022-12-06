import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
// Question 1 testing
//        float[][] A1 =Matrix.A1;
//        Matrix.ForwardElimination(A1);
// Question 2 testing
//        float[][] A2 = Matrix.A2;
//        Matrix.BetterForwardElimination(A2);

// Question 3
        System.out.println("Question 3");
        System.out.println();
        float[][] A3 = Matrix.A3;
        Matrix.GaussJordenElimination(A3);

        System.out.println("---------------------------------------------------------");
// Question 4
        System.out.println("Question 4");
        Room.question4(Room.assignmentArray);

    }



}

