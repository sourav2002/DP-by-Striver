
//here we used que 26 tabulation
public class _27longestCommonSubString {
    public static void main(String[] args) {
        String s = "abcd";
        String t = "abxd";
        int n= s.length();
        int m = t.length();
        int[][] dp = new int[n+1][m+1];

        for(int j=0; j<=m;j++) dp[0][j] = 0;
        int ans = 0;
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                    ans = Math.max(ans,dp[i][j]);
                }else{
                    dp[i][j] = 0;
                }
            }
        }
        System.out.println("common substring length is : " + ans);
    }
}
