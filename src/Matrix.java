public class Matrix {
    public static float[][] A1 = {
            {1,1,1,6},
            {1,1,2,9},
            {1,2,3,14}};
    public static float[][] A2 = {
            {1,1,1,6},
            {1,1,2,9},
            {2,2,3,15}};

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
                print2DArray(out);

            }

        }
        print2DArray(out);
        return out;
    }

    //Better^2ForwardElimation: version of BetterForwardElimination that actually works
    public static float[][] BetterBetterForwardElimination(float[][] A) {
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
                print2DArray(out);

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
            print2DArray(out);
            return out;


        
    }

}
