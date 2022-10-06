public class _26printLongestCommonSubseq {
    public static void main(String[] args) {
        String s = "abcde";
        String t = "bdgek";
        int n= s.length();
        int m = t.length();

        int[][] dp = new int[m+1][m+1];
        for(int j=0; j<=m; j++) dp[0][j] = 0;

        for (int i=1; i<=n; i++){
            for (int j=1; j<=m; j++){
                if(s.charAt(i-1) == t.charAt(j-1)) dp[i][j] = 1 + dp[i-1][j-1];
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        String ans = "";
        while( n >= 1 && m >= 1){

            if(s.charAt(n-1) == t.charAt(m-1)){
                ans = s.charAt(n-1) + ans;
                n--;
                m--;
                continue;
            }
            if(dp[n-1][m] > dp[n][m-1]){
                n--;
            }else{
                m--;
            }
        }
        System.out.println("common sub string is : "+ ans);
    }
}
