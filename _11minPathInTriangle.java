/*
 * We are given a Triangular matrix. We need to find the minimum 
 * path sum from the first row to the last row.
 * At every cell we can move in only two directions: either 
 * to the bottom cell (↓) or to the bottom-right cell(↘)
 */

public class _11minPathInTriangle {
    public static void main(String[] args) {
        int[][] arr = {
            {1,-1,-1,-1},
            {2,3,-1,-1},
            {3,6,7,-1},
            {8,9,6,10}
        };
        int m = arr.length;
        int n = arr[0].length;

        System.out.println(spaceOpti(arr, m, n));
        System.out.println(tabu(arr,m,n));
        System.out.println(memo(arr, 0, 0, new int[m][n]));
        System.out.println(rec(arr,0, 0));
    }

    public static int rec(int[][]arr, int i, int j){
        if(i == arr.length-1) return arr[i][j];

        if(j > i) {
            return rec(arr, i+1, j);
        }
        int right = arr[i][j] + rec(arr, i, j+1);
        int down = arr[i][j] + rec(arr, i+1, j);

        return Math.min(right, down);
    }

    public static int memo(int[][]arr, int i, int j, int[][]dp){
        if(i == arr.length-1) return arr[i][j];

        if(j > i) {
            return rec(arr, i+1, j);
        }
        if(dp[i][j] != 0) return dp[i][j];
        int right = arr[i][j] + memo(arr, i, j+1,dp);
        int down = arr[i][j] + memo(arr, i+1, j,dp);

        return dp[i][j] = Math.min(right, down);
    }


    public static int tabu(int[][] arr, int m, int n){
        int[][] dp = new int[n][n];
        for(int j=0; j<m; j++){
            dp[n-1][j] = arr[n-1][j];
        }

        for(int i=n-2; i>=0; i--){
            for(int j=i; j>=0; j--){
                int right = 0, bottom = 0;
                bottom = arr[i][j] + dp[i+1][j];
                if(i != j) right = arr[i][j] + dp[i][j+1];

                if(bottom == 0 && right != 0) dp[i][j] = right;
                else if(right == 0 && bottom != 0) dp[i][j] = bottom;
                else if(bottom !=0 && right !=0){
                    dp[i][j] = Math.min(bottom, right);
                }
            }
        }
        return dp[0][0];
    }

    public static int spaceOpti(int[][] arr, int m, int n){
        int[] prev = new int[n];
        for(int i=n-1; i>=0; i--){
            int[] temp = new int[n];
            for(int j=i; j>=0; j--){
                if(i == n-1){
                    temp[j] = arr[i][j];
                    continue;
                }
                int right = 0, down = 0;
                down = arr[i][j] + prev[j];
                if(i != j) {
                    right = arr[i][j] + temp[j+1];
                }
                if(right == 0) temp[j] = down;
                else temp[j] = Math.min(down, right);
            }
            prev = temp;
        }
        return prev[0];
    }
}
