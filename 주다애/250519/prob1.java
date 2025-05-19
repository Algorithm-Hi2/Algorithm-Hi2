import java.util.*;
class Solution {
    public int solution(int[][] scores) {
        int answer = 0;
        int a = scores[0][0];
        int b = scores[0][1];
        int sum = a + b;
        int rank = 1;
        int temp = 0;
        Arrays.sort(scores, (x, y) -> x[1] == y[1] ? x[0] - y[0] : y[1] - x[1]);
        for(int[] sc : scores) {
            if(a < sc[0] && b < sc[1]) return -1;
            
            if(temp <= sc[0]) {
                temp = sc[0];
                if(sc[0] + sc[1] > sum) rank++;
            }
        }
        return rank;
    }
}
