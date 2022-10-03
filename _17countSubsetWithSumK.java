public class _17countSubsetWithSumK {
    public static void main(String[] args) {
        int [] arr = {1,2,2,3};
        int n = arr.length;
        int tar = 4;
        int[][] dp = new int[n][tar+1];
        int ans =  solve(arr,tar,n-1,dp);
        System.out.println("ans is "+ ans);
    }

    public static int solve(int arr[], int t, int i, int[][] dp){
        if(t == 0) return 1;
        if(i == 0) return t == arr[i] ? 1 : 0;
        if(dp[i][t] != 0) return dp[i][t];
        int take = 0;
        if(arr[i] <= t) take = solve(arr, t - arr[i], i-1, dp);
        int not = solve(arr, t, i-1, dp);
        return dp[i][t] = take + not;
    }
}
