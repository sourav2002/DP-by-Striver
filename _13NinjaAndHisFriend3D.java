/*
We are given an ‘N*M’ matrix. Every cell of the matrix has some chocolates on it,
mat[i][j] gives us the number of chocolates. We have two friends ‘Alice’ and ‘Bob’.
initially, Alice is standing on the cell(0,0) and Bob is standing on the cell(0, M-1).
Both of them can move only to the cells below them in these three directions: to the bottom cell (↓),
to the bottom-right cell(↘), or to the bottom-left cell(↙).

When Alica and Bob visit a cell, they take all the chocolates from that cell with them.
It can happen that they visit the same cell, in that case, the chocolates need to be considered only once.
 */

public class _13NinjaAndHisFriend3D {
    public static void main(String[] args) {
        int [][] arr = {
                {2,3,1,2},
                {3,4,2,2},
                {5,6,3,5}
        };

        int n = arr.length;
        int m = arr[0].length;
        System.out.println("rec : "+ rec(arr, n,m , 0, 0, m-1) );
        System.out.println("memo :" + memo(arr,n,m,0,0,m-1, new int[n][m][m]));
        System.out.println("tabu : "+ tabu(arr, n, m));

    }

    public static int rec(int [][] arr, int n, int m, int i, int j1, int j2){

        if(j1 < 0 || j2 < 0 || j1 >= m || j2 >= m){
            return (int)(Math.pow(-10, 9));
        }
        if(i == n-1){
            if(j1 == j2) return arr[i][j1];
            return arr[i][j1] + arr[i][j2];
        }

        int maxi = Integer.MIN_VALUE;
        for (int di = -1; di <= 1; di++){
            for (int dj = -1; dj <= 1; dj++){
                int ans;
                if (j1 == j2)
                    ans =  arr[i][j1] + rec(arr,n, m,i+1,j1+di, j2+dj);
                else
                    ans =  arr[i][j1]+arr[i][j2] + rec(arr,n,m,i+1,j1+di, j2+dj);
                maxi = Math.max(maxi, ans);
            }
        }
        return maxi;
    }

    public static int memo(int [][] arr, int n, int m, int i, int j1, int j2, int[][][] dp){

        if(j1 < 0 || j2 < 0 || j1 >= m || j2 >= m){
            return (int)(Math.pow(-10, 9));
        }
        if(i == n-1){
            if(j1 == j2) return arr[i][j1];
            return arr[i][j1] + arr[i][j2];
        }

        if(dp[i][j1][j2] != 0) return dp[i][j1][j2];

        int maxi = Integer.MIN_VALUE;
        for (int di = -1; di <= 1; di++){
            for (int dj = -1; dj <= 1; dj++){
                int ans;
                if (j1 == j2)
                    ans =  arr[i][j1] + memo(arr,n, m,i+1,j1+di, j2+dj, dp);
                else
                    ans =  arr[i][j1]+arr[i][j2] + memo(arr,n,m,i+1,j1+di, j2+dj, dp);
                maxi = Math.max(maxi, ans);
            }
        }
        return dp[i][j1][j2] =  maxi;
    }

    public static int tabu(int[][] grid, int n, int m){
        int dp[][][] = new int[n][m][m];

        for (int j1 = 0; j1 < m; j1++) {
            for (int j2 = 0; j2 < m; j2++) {
                if (j1 == j2)
                    dp[n - 1][j1][j2] = grid[n - 1][j1];
                else
                    dp[n - 1][j1][j2] = grid[n - 1][j1] + grid[n - 1][j2];
            }
        }

        //Outer Nested Loops for travering DP Array
        for (int i = n - 2; i >= 0; i--) {
            for (int j1 = 0; j1 < m; j1++) {
                for (int j2 = 0; j2 < m; j2++) {

                    int maxi = Integer.MIN_VALUE;

                    //Inner nested loops to try out 9 options
                    for (int di = -1; di <= 1; di++) {
                        for (int dj = -1; dj <= 1; dj++) {

                            int ans;

                            if (j1 == j2)
                                ans = grid[i][j1];
                            else
                                ans = grid[i][j1] + grid[i][j2];

                            if ((j1 + di < 0 || j1 + di >= m) ||
                                    (j2 + dj < 0 || j2 + dj >= m))

                                ans += (int) Math.pow(-10, 9);
                            else
                                ans += dp[i + 1][j1 + di][j2 + dj];

                            maxi = Math.max(ans, maxi);
                        }
                    }
                    dp[i][j1][j2] = maxi;
                }
            }
        }

        return dp[0][0][m - 1];
    }

}
