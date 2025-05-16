import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        List<int[]> ranking = new ArrayList<>();
        Map<Integer, List<int[]>> map = new TreeMap<>(Comparator.reverseOrder());
        
        for (int[] score : scores) {
            if (!map.keySet().contains(score[0] + score[1])) {
                map.put(score[0] + score[1], new ArrayList<>());
            }
            map.get(score[0] + score[1]).add(score);
        }
        
        for (int sum : map.keySet()) {
            if (ranking.isEmpty()) {
                ranking.addAll(map.get(sum));
            } else {
                List<int[]> temp = new ArrayList<>();
                for (int[] candidate : map.get(sum)) {
                    boolean flag = true;
                    for (int[] ranker : ranking) {
                        if (candidate[0] < ranker[0] && candidate[1] < ranker[1]) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        temp.add(candidate);
                    }
                }
                ranking.addAll(temp);
            }
        }
        
        
        Collections.sort(ranking, (o1, o2) -> (o2[0] + o2[1]) - (o1[0] + o1[1]));
        int rank = 1;
        int prevScore = ranking.get(0)[0] + ranking.get(0)[1];
        for (int i = 0; i < ranking.size(); i++) {
            int sum = ranking.get(i)[0] + ranking.get(i)[1];
            if (sum != prevScore) {
                rank = i + 1;
                prevScore = sum;
            }
            if (sum == scores[0][0] + scores[0][1]) {
                return rank;
            }
        }
        
        return rank;
    }
}