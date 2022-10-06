// this problem is same as longest common subsequence
// if we take a new string exactly reverse of first string, and there is any palindrome then we can get it using lcs

public class _28longestPalindromeSubsequence {
    public static void main(String[] args) {
        String s = "bbabcbcab";
        int n = s.length();
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        int dp[][] = new int[n+1][n+1];

        for(int j=0; j<=n; j++){
            dp[0][j] = 0;
        }

        for(int i=1; i<=n; i++){
            for(int j=1; j<=n;j++){
                if(s.charAt(i-1) == sb.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        System.out.println("length of lcs is : "+dp[n][n]);
        int x = n, y = n;
        String ans = "";
        while(x > 0 && y > 0){
            if(s.charAt(x-1) == sb.charAt(y-1)){
                ans = s.charAt(x-1) + ans;
                x--;
                y--;
                continue;
            }
            if(dp[x-1][y] > dp[x][y-1]){
                x--;
            }else{
                y--;
            }
        }
        System.out.println("palindrome string is : "+ ans);
    }
}
