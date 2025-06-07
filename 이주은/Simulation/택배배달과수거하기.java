//https://school.programmers.co.kr/learn/courses/30/lessons/150369

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        int d = n-1;
        int p = n-1;

        for(; d >= 0; d--) {
            if(deliveries[d] != 0)
                break;
        }

        for(; p >= 0; p--) {
            if(pickups[p] != 0)
                break;
        }

        while(d >= 0 || p >= 0) {
            answer += Math.max(d, p) + 1;

            int remain = cap;
            for(; d >= 0; d--) {
                if(deliveries[d] <= remain) {
                    remain -= deliveries[d];
                    deliveries[d] = 0;
                }
                else {
                    deliveries[d] -= remain;
                    break;
                }
            }

            remain = cap;
            for(; p >= 0; p--) {
                if(pickups[p] <= remain) {
                    remain -= pickups[p];
                    pickups[p] = 0;
                }
                else {
                    pickups[p] -= remain;
                    break;
                }
            }
        }

        return answer << 1;
    }
}
