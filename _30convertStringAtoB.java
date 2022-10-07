/*

 */


public class _30convertStringAtoB {
    public static void main(String[] args) {
        String s = "abcd";
        String t = "bed";
        int n = s.length();
        int m = t.length();
        int lcs = space(s,t,n,m);
//        int lcs = lcs(s,t,n,m);
        int ans =  ( (n-lcs) + (m-lcs));
        System.out.println("tabu : " + ans);
    }

    public static int space(String s, String t, int n, int m){
        int[] prev = new int[m];

        for(int j=0; j<m; j++){
            if(s.charAt(0) == t.charAt(j)) prev[j] = 1;
            if(j>0 && prev[j-1] != 0) prev[j] = 1;
        }
        for(int i=1; i<n; i++){
            int[] curr = new int[m];
            if(s.charAt(i) == t.charAt(0) || prev[0] != 0) curr[0] = 1;
            for(int j=1; j<m; j++){
                if(s.charAt(i) == t.charAt(j)){
                    curr[j] = 1 + prev[j-1];
                }else{
                    curr[j] = Math.max(prev[j], curr[j-1]);
                }
            }
            prev = curr;
        }
        return prev[m-1];
    }

    public static int lcs(String s, String t, int n, int m){
        int[][]dp = new int[n][m];
        for(int i=0; i<n; i++){
            if(s.charAt(i) == t.charAt(0)) dp[i][0] = 1;
            if(i>0 && dp[i-1][0] != 0) dp[i][0] = dp[i-1][0];
        }
        for(int j=0; j<m; j++){
            if(s.charAt(0) == t.charAt(j)) dp[0][j] = 1;
            if(j>0 && dp[0][j-1] != 0) dp[0][j] = 1;
        }

        for(int i=1; i<n; i++){
            for(int j=1; j<m; j++){
                if(s.charAt(i) == t.charAt(j)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[n-1][m-1];
    }
}
