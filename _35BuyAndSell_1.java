//You are given an array prices where prices[i] is the price of a given stock on the ith day.
//
//        You want to maximize your profit by choosing a single day to buy one stock and choosing a
//        different day in the future to sell that stock.
//
//        Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

public class _35BuyAndSell_1 {
    static int maxi = 0;

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int n = prices.length;

        System.out.println("recursion : " + maxProfit(prices));
        System.out.println("tabulation : "+ tabu(prices, n));
        System.out.println("space optimization : " + spaceOptimization(prices, n));
    }

    public static int maxProfit(int[] prices) {
        return solve(prices, 0, Integer.MAX_VALUE);
    }

    public static int solve(int[] prices, int index, int min) {
        if (index == prices.length)
            return 0;

//        int mini = Math.min(min, prices[index]);
//        int maxProfit = solve(prices, index + 1, mini);
//        maxProfit = Math.max(maxProfit, prices[index] - min);
//        return maxProfit;
//        return Math.max(solve(prices, index+1, mini), prices[index] - min);
        return Math.max(solve(prices, index+1, Math.min(min, prices[index])), prices[index] - min);

    }

    public static int tabu(int[] arr, int n){
        if(n==0) return 0;
        int[] dp = new int[n];
        int min = arr[0];

        for(int i=1; i<n; i++){
            dp[i] = Math.max(dp[i-1], arr[i] - min);
            min = Math.min(min, arr[i]);
        }

        return dp[n-1];
    }

    // storing minTillNow is a part of dp
    public static int spaceOptimization(int[] arr, int n){
        int maxProfit = 0;
        int minTillNow = arr[0];

        for(int i=1; i<n; i++){
            minTillNow = Math.min(minTillNow, arr[i]);
            maxProfit = Math.max(arr[i] - minTillNow, maxProfit);
        }
        return maxProfit;
    }

}
