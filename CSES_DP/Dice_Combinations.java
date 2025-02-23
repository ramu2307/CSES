package CSES_DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Dice_Combinations {

    final static int MOD = 1000000000;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n + 1];
        dp[0] = 1;
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= 6; j++) {
                if(j <= i) {
                    dp[i] = (dp[i] + dp[i - j]) % MOD;
                }
                else {
                    break;
                }
            }
        }

        System.out.println(dp[n]);
    }
}
