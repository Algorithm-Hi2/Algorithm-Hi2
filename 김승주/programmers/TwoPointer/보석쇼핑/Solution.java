import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Map<String, Integer> gemMap = new HashMap<>(); // (보석 이름, 보석 번호)
        List<Integer> gemList = new ArrayList<>(); // 보석 번호로 변환된 진열 순서
        gemList.add(-1); // 1번 진열대부터 진열하기 위해 0번 인덱스에는 임의의 값 저장
        int num = 0; // 보석의 종류 수
        
        // 새로운 보석 종류를 발견할 때마다 gemMap에 넣고 번호 부여
        // String 타입의 보석을 int 타입의 번호(num)로 치환해 gemList에 추가
        for (String gem : gems) {
            if (!gemMap.keySet().contains(gem)) {
                gemMap.put(gem, ++num);
            }
            gemList.add(gemMap.get(gem));
        }
        
        int[] result = {1, gemList.size()};
        int[] status = new int[num + 1]; // front와 rear 사이에 각 보석 번호가 몇 개 존재하는지?
        Set<Integer> contained = new HashSet<>(); // front와 rear 사이에 존재하는 보석들의 번호
        
        status[gemList.get(1)]++;
        contained.add(gemList.get(1));
        for (int front = 1, rear = 1; rear < gemList.size();) {
            // front와 rear 사이에 진열된 모든 종류의 보석이 적어도 1개 이상 포함되어 있는 경우
            if (contained.size() == num) {
                // 현재 구간이 이전에 발견한 구간보다 짧은 경우 result 갱신
                if (rear - front + 1 < result[1] - result[0] + 1) {
                    result[0] = front;
                    result[1] = rear;
                }
                // front를 1 증가시킴으로써 구간 길이를 줄여 재탐색
                --status[gemList.get(front)];
                if (status[gemList.get(front)] == 0) {
                    contained.remove(gemList.get(front));
                }
                front++;
            }
            
            // front와 rear 사이에 포함되지 않은 보석 종류가 있는 경우
            // rear를 1 증가시킴으로써 구간 길이를 늘려 재탐색
            else if (++rear < gemList.size()) {
                ++status[gemList.get(rear)];
                contained.add(gemList.get(rear));
            }
        }
        
        return result;
    }
}