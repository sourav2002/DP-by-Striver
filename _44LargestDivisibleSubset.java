import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _44LargestDivisibleSubset {
    public static void main(String[] args) {
        int[] nums = {1, 8, 7, 4, 16};
        int n = nums.length;
        int[] dp = new int[n];
        int[] hash = new int[n];
        Arrays.sort(nums);
        int max = 0, index = -1;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            hash[i] = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0) {
                    if (1 + dp[j] > dp[i]) {
                        dp[i] = dp[j] + 1;
                        hash[i] = j;
                    }
                }
            }
            if (dp[i] > max) {
                max = dp[i];
                index = i;
            }
        }
        List<Integer> res = new ArrayList<>();
        while (index != -1) {
            res.add(nums[index]);
            index = hash[index];
        }
        System.out.println(res);
    }
}
