public class Matrix {



    public static float[][] test = {
            {1,0,3,1},
            {0,-2,-1,0},
            {1,-3,2,2},
    };
    public static float[][] A1 = {
            {1,1,1,6},
            {1,1,2,9},
            {1,2,3,14}};
    public static float[][] A2 = {
            {1,1,1,6},
            {1,1,2,9},
            {2,2,3,15}};
    public static float[][] A3 = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 364},
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
            {0, 0, 1, 1, 0, 0, 0 ,0, 0, 0, 0, 0, 16},
            {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 36},
            {0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 64},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 100},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 79},
            {0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 61},
            {0, 0, 0, 0, 0, 4, - 3, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 3, - 2, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 1 ,- 1, 0, 0, 0},
            {1, - 1, 1, - 1, 1, - 1, 1, - 1, 1, - 1, 1, - 1, - 42}


    };

    public static void print2DArray(float[][] anArray){
        // width: w
        // length: l
        int w = anArray[0].length;
        int l = anArray.length;
        for(int i = 0;i< l;i++){
            for(int j = 0; j<w;j++) {
                System.out.print(anArray[i][j] + " | ");
            }
            System.out.println();

        }
        System.out.println();
    }
    public static void print2DArrayInt(float[][] anArray){
        // width: w
        // length: l
        int w = anArray[0].length;
        int l = anArray.length;
        for(int i = 0;i< l;i++){
            for(int j = 0; j<w;j++) {
                System.out.print((int)anArray[i][j] + " | ");
            }
            System.out.println();

        }
        System.out.println();
    }

    public static float[][] ForwardElimination(float[][] A){

        int n = A.length;
        // width is n + 1

        for(int i = 0; i<n-1;i++){
            for(int j = i+1;j<n;j++){
                for(int k = i;k<n+1;k++){
                    A[j][k] = A[j][k] - A[i][k]*A[j][i]/A[i][i];
                }
            }
        }
        print2DArray(A);
        return A;
    }
    // helper function (made with help of Alizade)
    public static float[][] BetterForwardElimination(float[][] A) {
        float[][] out = A;

        for(int i = 0; i < A.length - 1; i++) {
            int pivotRow = i;

            for(int j = i + 1; j < A.length; j++) {
                // if you see any values in the jth row that are larger in magnitude to the pivotRow, change the pivot row to the jth row
                if(Math.abs(out[j][i]) > Math.abs(out[pivotRow][i])) {
                    pivotRow = j;
                }
            }
            // swap pivotRow and ith row
            for(int k = i; k < A.length + 1; k++) {
                //swap(A[i, k], A[pivotrow, k])
                float temp = out[i][k];
                out[i][k] = out[pivotRow][k];
                out[pivotRow][k] = temp;
            }

            for(int j = i + 1; j < A.length; j++) {
                float  temp = out[j][i] / out[i][i];

                for(int k = i; k < A.length + 1; k++) {
                    out[j][k] = out[j][k] - out[i][k] * temp;
                }
//                print2DArray(out);

            }

        }
        print2DArray(out);
        return out;
    }
    //
    public static float[][] GaussJordenElimination(float[][] A){
//        elimination in that the elements above the main diagonal of the coefficient matrix are made zero at the same time
//              by the same use of a pivot row as the elements below the main diagonal.
//        Thus, the coefficient matrix is transformed into a diagonal matrix rather than an upper-triangular matrix.
//        Furthermore, if each pivot row is “divided by” its pivot (leading non-zero entry) prior to its use
//        as a pivot row, the coefficient matrix is transformed into the identity matrix,
//        and the back substitution step may be dispensed with entirely. That is, the solution x is simply the last column of the (transformed) augmented system matrix.
        float[][] out = A;
// changed the max length from "A.length - 1" to "A.length"
        for(int i = 0; i < A.length; i++) {
            int pivotRow = i;




            for(int j = i + 1; j < A.length; j++) {
                // if you see any values in the jth row that are larger in magnitude to the pivotRow, change the pivot row to the jth row
                if(Math.abs(out[j][i]) > Math.abs(out[pivotRow][i])) {
                    pivotRow = j;
                }
            }
            // for each pivot row, divide by its pivot
            float pivotDiag = out[pivotRow][i];
            for(int d = 0;d<A.length +1;d++){
                out[pivotRow][d] = out[pivotRow][d]/pivotDiag;
            }
            // swap pivotRow and ith row
            for(int k = i; k < A.length + 1; k++) {
                //swap(A[i, k], A[pivotrow, k])
                float temp = out[i][k];
                out[i][k] = out[pivotRow][k];
                out[pivotRow][k] = temp;
            }
// change: I made j "0" instead of i+1  and give an if condition for j!=i. This way, I can get rid of all the values in a column except for the diagonal pairs
            for(int j = 0; j < A.length; j++) {
                if(j!=i){
                    float  temp = out[j][i] / out[i][i];

                    for(int k = i; k < A.length + 1; k++) {
                        out[j][k] = out[j][k] - out[i][k] * temp;
                    }

                }

            }



        }
        // round all numbers to integers
        for(int i  = 0; i< A.length;i++){
            for(int j = 0; j<A.length+1;j++){
                out[i][j] =  Math.round(out[i][j]);
            }
        }
        // small detail: make all of the "-0's" in the last row "0"
//            for (int w = 0; w< A.length -2; w++){
//                out[A.length-1][w] = 0;
//            }
        print2DArrayInt(out);
        return out;



    }

}
