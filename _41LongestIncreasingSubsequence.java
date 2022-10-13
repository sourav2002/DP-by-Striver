import java.util.Arrays;

public class _41LongestIncreasingSubsequence {
    public static void main(String[] args) {
        int arr[] = {0,1,0,3,2,3};
        int n = arr.length;
        int[][] dp = new int[n][n];
        for(int[] a : dp) Arrays.fill(a, -1);
        System.out.println("memo : " + solve(arr,0, -1, dp));
        int[] optiDp = new int[n];
        Arrays.fill(optiDp, -1);
        System.out.println("optimized memo : " + optimizedMemo(arr, 0, -1, optiDp));
        System.out.println("tabu : "+ tabu(arr));
    }

    public static int solve(int[] arr, int i, int prev, int[][] dp) {
        if (i == arr.length) return 0;
        if(dp[i][prev+1] != -1) return dp[i][prev+1];

        int not =  solve(arr, i + 1, prev, dp);
        int take = 0;
        if (prev == -1 || arr[i] > arr[prev]) {
            take = 1 + solve(arr, i + 1, i, dp);
        }
        return dp[i][prev+1] =  Math.max(take, not);
    }

    public static int optimizedMemo(int[] arr, int i, int prev, int[] dp) {
        if (i == arr.length) return 0;
        if(dp[prev+1] != -1) return dp[prev+1];

        int not =  optimizedMemo(arr, i + 1, prev, dp);
        int take = 0;
        if (prev == -1 || arr[i] > arr[prev]) {
            take = 1 + optimizedMemo(arr, i + 1, i, dp);
        }
        return dp[prev+1] =  Math.max(take, not);
    }

    public static int tabu(int[] arr){
        int ans = 1;
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for(int i=0; i<n; i++){
            for(int j=0; j < i; j++){
                if(arr[i] > arr[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    ans = Math.max(ans, dp[i]);
                }
            }
        }
        return ans;
    }
}

/*
[0,1,0,3,2,3]

dp = [1,2,1,3,3,1]

i = 0, j = 0 no change
i = 1 j = 0 dp[1] = 1,2  = 2
i = 2 j = 0 and 1  dp[2] = 1
i = 3 j = 0,1,2 dp[3] = 2,3,2 = 3
i = 4 j = 0,1,2,3 dp[4] = 2,3,2,0 = 3
i = 5 j = 0,1,2,3,4 dp[5] = 2,3,2,0,4 = 4
 */