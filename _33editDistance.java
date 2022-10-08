//We are given two strings ‘S1’ and ‘S2’. We need to convert S1 to S2. The following three operations are allowed:
//
//        Deletion of a character.
//        Replacement of a character with another one.
//        Insertion of a character.
//        We have to return the minimum number of operations required to convert S1 to S2 as our answer.

import java.util.*;

public class _33editDistance {
    public static void main(String[] args) {
        String s = "horse";
        String t = "ros";
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n][m];
        for(int[] arr : dp){
            Arrays.fill(arr, -1);
        }
        System.out.println("memo " + memo(s,t,n-1,m-1, dp));
        System.out.println("tabu :" + tabu(s,t,n,m));
    }

    public static int memo(String s, String t, int i, int j, int[][]dp){
        if(i<0)
            return j+1;
        if(j<0)
            return i+1;

        if(dp[i][j] != -1)return dp[i][j];

        if(s.charAt(i) == t.charAt(j)){
            return dp[i][j] = memo(s,t,i-1, j-1, dp);
        }else{
            return dp[i][j] = Math.min(1+memo(s,t,i-1, j,dp), Math.min(1+memo(s,t, i-1, j-1,dp), 1+memo(s,t,i,j-1,dp)));
        }
    }

    public static int tabu(String s, String t, int n, int m){
        int[][] dp = new int[n+1][m+1];
        for(int i=1; i<=n; i++){
            dp[i][0] = i;
        }
        for(int j=1; j<=m; j++){
            dp[0][j] = j;
        }

        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(s.charAt(i-1) == t.charAt(j-1)){
                     dp[i][j] = dp[i-1][ j-1];
                }else{
                     dp[i][j] = 1+  Math.min(dp[i-1][j], Math.min(dp[i-1][j-1], dp[i][j-1]));
                }
            }
        }
        return dp[n][m];
    }
}
