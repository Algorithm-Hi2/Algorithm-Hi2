import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        Map<String, Integer> map = new HashMap<>();
        int size = map.size();
        int left = 0;
        int right = gems.length;
        int cnt = 0; // 겹치는 개수
        Set<String> set = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        for(String g : gems) set.add(g);
        int idx = 0;
        for(String g : gems) {
            map.put(g, map.getOrDefault(g, 0) + 1);
            q.offer(g);
            idx += 1;
            while(true) {
                String now = q.peek();
                int v = map.get(now);
                // 이미 map 안에 들어있으면
                // 앞에서부터 뺀다. -> 어차피 앞에서부터 탐색하면서 최소 거리를 찾기 때문
                if(v > 1) {
                    q.poll();
                    map.put(now, map.getOrDefault(now, 0) - 1);
                    cnt += 1;
                }
                else break;
            }
            // 모든 보석을 포함하면
            if(map.size() == set.size()) {
                // right 범위를 줄일 수 있다면
                if(right > q.size())
                {
                    left = cnt;
                    right = q.size();
                }                
            }
        }
        int[] answer = {left + 1, left + right};
        return answer;
    }
}
