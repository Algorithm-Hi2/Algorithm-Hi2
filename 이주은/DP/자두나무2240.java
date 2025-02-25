import java.util.*;
import java.io.*;

class Main {

    static final int DISABLE = -1;
    
    static int T;
    static int W;
    static int[][] dp;
    static int[] info;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        info = new int[T+1];
        dp = new int[T+1][W+1];
        
        for(int i=1; i<=T; i++) {
            info[i] = Integer.parseInt(br.readLine());
        }
        
        Arrays.fill(dp[0], 1, W+1, DISABLE);
        
        for(int t=1; t<=T; t++) {
            dp[t][0] = dp[t-1][0] + (info[t] == 1 ? 1 : 0);
            
            for(int i=1; i<=W; i++) {
                dp[t][i] = Math.max(dp[t-1][i-1], dp[t-1][i]);

                if(dp[t][i] != DISABLE)
                     dp[t][i] += (info[t] == (i%2) + 1 ? 1 : 0);
            }
        }

        int answer = 0;
        for(int i=0; i<=W; i++) {
            answer = Math.max(answer, dp[T][i]);
        }

        System.out.println(answer);
    }
}
