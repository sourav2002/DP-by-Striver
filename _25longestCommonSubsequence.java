import java.util.Arrays;
public class _25longestCommonSubsequence {
    public static void main(String[] args) {
        String s = "adebc";
        String t = "dcadb";
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n][m];
        System.out.println("rec : "+ rec(s,t,n-1, m-1));
        System.out.println("memo : "+ memo(s,t,n-1,m-1, dp));
        System.out.println("tabu : "+ tabu(s,t,n,m));
        System.out.println("space : " + space(s,t,n,m));
    }

    public static int rec(String s, String t, int i, int j){
        if(i < 0 || j < 0) return 0;

        if(s.charAt(i) == t.charAt(j)){
            return 1 + rec(s,t,i-1, j-1);
        }
        return Math.max(rec(s,t, i, j-1), rec(s,t,i-1,j));
    }

    public static int memo(String s, String t, int i, int j, int[][] dp){
        if(i < 0 || j < 0) return 0;
        if(dp[i][j] != 0) return dp[i][j];
        if(s.charAt(i) == t.charAt(j)){
            return dp[i][j] =  1 + memo(s,t,i-1, j-1, dp);
        }
        return dp[i][j] =  Math.max(memo(s,t, i, j-1,dp), memo(s,t,i-1,j,dp));
    }

    // TODO: 07/10/2022  unknown error while using general tabulation approach so use shifting of index technique
    //  where i and j means index[i-1] and index[j-1] to tackle if(i < 0 ) testcase of recursion
    //  because we can't store negative index in dp.
    public static int tabu(String s, String t, int n, int m){
        int[][] dp = new int[n+1][m+1];
        for(int i=0; i<n; i++){
            if(s.charAt(i) == t.charAt(0)) dp[i][0] = 0;
        }
        for(int j=0; j<m; j++){
            if(s.charAt(0) == t.charAt(j)) dp[0][j] = 0;
        }

        for(int i=1; i<n; i++){
            for(int j=1; j<m; j++){
                if(s.charAt(i) == t.charAt(j)){
                    dp[i][j] =  dp[i-1][j-1] + 1;
                    continue;
                }
                dp[i][j] =  Math.max(dp[i][j-1], dp[i-1][j]);
            }
        }
//        System.out.println(Arrays.deepToString(dp));
        return dp[n-1][m-1];
    }

    public static int space(String s, String t,int n,int m){
        int[] prev = new int[m+1];
        int[] curr = new int[m+1];
        for(int j=0; j<=m; j++){
            prev[j] = 0;
        }

        for(int i=1;i<=n;i++){
            for(int j = 1; j<=m; j++){
                if(s.charAt(i-1) == t.charAt(j-1)) {
                    curr[j] = 1 + prev[j-1];
                    continue;
                }
                curr[j] = Math.max(curr[j-1], prev[j]);
            }
            prev=curr.clone();
        }
        return prev[m];
    }
}
