public class _04frogKJumps {
    public static void main(String[] args) {
        int n = 6;
        int k = 2;
        int [] arr = {30,10,60 , 10 , 60 , 50};
        int[] dp = new int[n];
        System.out.println(solveTabu(n-1, k, arr, dp));
        System.out.println(solveMemo(n-1, k, arr, dp));
        System.out.println(solveRec(n-1,k,arr));
    }

    public static int solveRec(int n, int k, int[] arr){
        if(n == 0) return 0;
        int mini = Integer.MAX_VALUE;
        for(int i=1; i<=k; i++){
            if(n-i >= 0){
                int jump = solveRec(n-i, k, arr) + Math.abs(arr[n]-arr[n-i]);
                mini = Math.min(mini, jump);
            }
        }
        return mini;
    }

    public static int solveMemo(int n, int k, int[] arr, int [] dp){
        if(n == 0) return 0;
        if(dp[n] != 0) return dp[n];
        int mini = Integer.MAX_VALUE;
        for(int i=1; i<=k; i++){
            if(n-i >= 0){
                int jump = solveMemo(n-i, k, arr, dp) + Math.abs(arr[n]-arr[n-i]);
                mini = Math.min(mini, jump);
            }
        }
        return dp[n] = mini;
    }

    public static int solveTabu(int n, int k, int[] arr, int[] dp){
        dp[0] = 0;
        for(int i=1; i<=n; i++){
            int mini = Integer.MAX_VALUE;
            for(int j=1; j<=k; j++){
                if(i-j>=0){
                    int jump = Math.abs(arr[i]- arr[i-j]) + dp[i-j];
                    mini = Math.min(mini, jump);
                }
            }
            dp[i] = mini;
        }
        return dp[n];
    }
}
