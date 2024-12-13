import java.util.*;

class Solution {
    public int solution(String dirs) {
        int answer = 0;
        
        // 2차원 배열이 아닌 x, y축으로 그려진 2차원 좌표축으로 생각
        int nowX = 0; // 현재 x 좌표
        int nowY = 0; // 현재 y 좌표
        
        Map<Character, int[]> directionMap = new HashMap<>();
        
        directionMap.put('U', new int[]{0, 1});
        directionMap.put('D', new int[]{0, -1});
        directionMap.put('L', new int[]{-1, 0});
        directionMap.put('R', new int[]{1, 0});
        
        Set<String> visitedSet = new HashSet<>();
        
        for (char dir: dirs.toCharArray()) {
            int[] move = directionMap.get(dir);
            int nextX = nowX + move[0];
            int nextY = nowY + move[1];
            
            if (nextX < -5 || nextY < -5 || nextX > 5 || nextY > 5) {
                continue;
            }
            
            // 경로 저장하기 (출발점 -> 도착점, 도착점 -> 출발점)
            String path1 = nowX + "," + nowY + "->" + nextX + "," + nextY;
            String path2 = nextX + "," + nextY + "->" + nowX + "," + nowY;
            visitedSet.add(path1);
            visitedSet.add(path2);
            
            nowX = nextX;
            nowY = nextY;
        }
        
        answer = visitedSet.size() / 2;
        
        return answer;
    }
}