public class Matrix {
static float[][] p1Matrix = {{1,1,1,6},{1,1,2,9},{2,2,3,15}};


    // helper function (made with help of Alizade)
    public static float[][] BetterForwardElimination(float[][] matrix) {
        float[][] out = matrix;

        for(int i = 0; i < matrix.length - 1; i++) {
            int pivotRow = i;

            for(int j = i + 1; j < matrix.length; j++) {
                if(Math.abs(out[j][i]) > Math.abs(out[pivotRow][i])) {
                    pivotRow = j;
                }
            }

            for(int k = i; k < matrix.length + 1; k++) {
                float temp = out[i][k];
                out[i][k] = out[pivotRow][k];
                out[pivotRow][k] = temp;
            }

            for(int j = i + 1; j < matrix.length; j++) {
                float temp = out[j][i] / out[i][i];

                for(int k = i; k < matrix.length + 1; k++) {
                    out[j][k] = out[j][k] - out[i][k] * temp;
                }
            }

        }

        return out;
    }

}
