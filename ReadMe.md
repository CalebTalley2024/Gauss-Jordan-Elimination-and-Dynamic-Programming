#HW 4

## Q1 (read last two  paragraph of wrong explanation)
- basically: the function just changes values to zero to make a triangular matrix. This matrix is not equivalent to our original matrix as non of the other values are changed
- the first change will make A[j,i] = 0
- when this equation is used after that, and we plug in A[j,i], all of the values will not change
- (show example heere)
- BetterForwardElimnation algorithm fixes this by changing A[j, i] / A[i, i] for each different j-value
## Q2
- The main problem here is that when A[i,i] = 0, the function will return "NaN" values for it's matrix cells at j = 2
- The equation that we were given has no upper triangular matrix, since all that is needed is that x+y = 3
- Solution: 
//Next: maek function to see all of the sum values in array
