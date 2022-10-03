/*
problem link -
https://www.codingninjas.com/codestudio/problems/partitions-with-given-difference_3751628?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos
 */

public class _18partitionWithGivenDiff {
    static int mod = 1000000000+7;

    public static void main(String[] args) {
        int [] arr = {0,0,1};
        int d = 1;
        int n = arr.length;

        int sum = 0;
        for(int i : arr) sum += i;
        int target = (sum-d)/2;
        int [][] dp = new int[n][target+1];
        System.out.println("memo : "+ memo(arr,n-1,target, dp));
        System.out.println("tabu : "+ tabu(arr,n,target));
        System.out.println("space opti : "+ space(arr,n,target));
        System.out.println("recursion : " + rec(arr,n-1,target) );
    }
    public static int rec(int[]arr, int n, int target ){
        if( n == 0 ){
            if(arr[0] == 0) return 2;
            if(target == 0 || arr[0] == target) return 1;
            return 0;
        }

        int take =0;
        if(arr[n] <= target) take = rec(arr,n-1, target - arr[n]);
        int not = rec(arr,n-1, target);
        return (take+not)% mod;
    }

    public static int memo(int[]arr, int n, int target , int [][] dp){
        if( n == 0 ){
            if(arr[0] == 0) return 2;
            if(target == 0 || arr[0] == target) return 1;
            return 0;
        }
        if(dp[n][target] != 0) return dp[n][target];
        int take =0;
        if(arr[n] <= target) take = memo(arr,n-1, target - arr[n], dp);
        int not = memo(arr,n-1, target, dp);
        return dp[n][target] =  (take+not)%mod;
    }


    public static int tabu(int[] arr, int n, int target){
        int[][] dp = new int[n][target+1];
        if(arr[0] == 0) dp[0][0] = 2;
        else dp[0][0] = 1;

        if(arr[0] != 0 &&  arr[0] <= target) dp[0][arr[0]] = 1;

        for(int i=1; i<n; i++){
            for(int t = 0; t<=target; t++){
                int take = 0;
                if(arr[i] <= t) take = dp[i-1][t-arr[i]];
                int not = dp[i-1][t];
                dp[i][t] = (take+not) % mod;
            }
        }
        return dp[n-1][target];
    }

    public static int space(int[] arr, int n, int target){
        int[] prev = new int[target+1];
        if(arr[0] == 0) prev[0] = 2;
        else prev[0] = 1;

        if(arr[0] != 0 &&  arr[0] <= target) prev[arr[0]] = 1;

        for(int i=1; i<n; i++){
            int[] temp = new int[target+1];
            for(int t = 0; t<=target; t++){
                int take = 0;
                if(arr[i] <= t) take = prev[t-arr[i]];
                int not = prev[t];
                temp[t] = (take+not) % mod;
            }
            prev = temp;
        }
        return prev[target];
    }
}
