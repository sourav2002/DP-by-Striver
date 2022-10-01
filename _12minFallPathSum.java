/*
We are given an ‘N*M’ matrix. We need to find the maximum path
sum from any cell of the first row to any cell of the last row.

At every cell we can move in three directions: to the bottom cell (↓),
to the bottom-right cell(↘), or to the bottom-left cell(↙).
 */



public class _12minFallPathSum {
    public static void main(String[] args) {
        int [][] mat = {
                {1,  2, 10,4},
                {100,3,  2,1},
                {1,  1, 20,2},
                {1,  2,  2,1}
        };
        solve(mat);
    }

    public static void solve(int[][] mat){
        int n = mat.length;
        int m = mat[0].length;
        int rec = Integer.MAX_VALUE;
        int [][] dp = new int[n][m];
        for(int j =0; j<m; j++){
            rec = Math.min(rec, getMinPathSum(mat, n,m, n-1, j));
        }
        System.out.println("Recursion : " + rec);
        int memo = Integer.MAX_VALUE;
        for(int j =0; j<m; j++){
            memo = Math.min(memo, solveMemo(mat,n-1, j, dp));
        }
        System.out.println("memo : "+ memo);
        int tabu = solveTabu(mat,n,m);
        System.out.println("Tabu : "+ tabu);
        System.out.println("Space optimization : " + spaceOptimization(mat, n, m));
    }



    private static int getMinPathSum(int[][] mat, int n, int m, int i, int j) {
        if(j < 0 || j >= m){
            return (int)Math.pow(10,9);
        }

        if(i == 0){
            return mat[0][j];
        }

        int up = mat[i][j] + getMinPathSum(mat, n,m,i-1, j);
        int left = mat[i][j] + getMinPathSum(mat, n, m, i-1, j-1);
        int right = mat[i][j] + getMinPathSum(mat, n, m, i-1, j+1);
        return Math.min(up, Math.min(left, right));
    }


    public static int solveMemo(int[][] mat, int i, int j, int[][] dp){
        if(j < 0 || j >= mat[0].length){
            return (int)Math.pow(10,9);
        }

        if(i == 0){
            return mat[0][j];
        }

        if(dp[i][j] !=0) return dp[i][j];

        int up = mat[i][j] + solveMemo(mat, i-1, j, dp);
        int left = mat[i][j] + solveMemo(mat,  i-1, j-1, dp);
        int right = mat[i][j] + solveMemo(mat,  i-1, j+1, dp);
        return dp[i][j] = Math.min(up, Math.min(left, right));
    }

    public static int solveTabu(int[][] mat, int n, int m){
        int[][] dp = new int[n][m];
        for(int j=0; j<m; j++){
            dp[0][j] = mat[0][j];
        }
        for (int i=1; i<n; i++){
            for(int j=0; j<m; j++){
                int up,left = Integer.MAX_VALUE,right= Integer.MAX_VALUE;
                up = mat[i][j] + dp[i-1][j];
                if(j > 0) left = mat[i][j] + dp[i-1][j-1];
                if(j < m-1) right = mat[i][j] + dp[i-1][j+1];
                dp[i][j] = Math.min(up, Math.min(left, right));
            }

        }
        int mini = Integer.MAX_VALUE;
        for(int i=0; i<m; i++){
            mini = Math.min(mini, dp[n-1][i]);
        }
        return mini;
    }

    public static int spaceOptimization(int[][] mat,int n, int m){
        int prev[] = new int[m];
        for (int j =0; j<m; j++){
            prev[j] = mat[0][j];
        }
        for (int i=1; i<n; i++){
            int[] temp = new int[m];
            for(int j=0; j<m; j++){
                int up,left = Integer.MAX_VALUE,right= Integer.MAX_VALUE;
                up = mat[i][j] + prev[j];
                if(j > 0) left = mat[i][j] + prev[j-1];
                if(j < m-1) right = mat[i][j] + prev[j+1];
                temp[j] = Math.min(up, Math.min(left, right));
            }
            prev = temp;
        }
        int mini = Integer.MAX_VALUE;
        for(int i=0; i<m; i++){
            mini = Math.min(mini, prev[i]);
        }
        return mini;
    }

}
