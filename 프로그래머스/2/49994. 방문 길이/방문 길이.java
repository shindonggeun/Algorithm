import java.util.*;

class Solution {
    public int solution(String dirs) {
        int answer = 0;
        
        // 2차원 배열이 아닌 x, y축으로 그려진 2차원 좌표축으로 생각
        int nowX = 0; // 현재 x 좌표
        int nowY = 0; // 현재 y 좌표
        
        // 방향 정보에 따라 이동하는 값(좌표) 저장하는 해시맵
        Map<Character, int[]> directionMap = new HashMap<>(); // key: 방향 정보, value: 방향 정보에 따른 좌표
        
        directionMap.put('U', new int[]{0, 1}); // 위 (x 변화 없음, y 증가)
        directionMap.put('D', new int[]{0, -1}); // 아래 (x 변화 없음, y 감소)
        directionMap.put('L', new int[]{-1, 0}); // 왼쪽 (x 감소, y 변화 없음)
        directionMap.put('R', new int[]{1, 0}); // 오른쪽 (x 증가, y 변화 없음)
        
        Set<String> visitedSet = new HashSet<>(); // 방문한 길을 저장하는 해시셋 (중복 제거)
        
        // 주어진 이동방향 명령들 탐색
        for (char dir: dirs.toCharArray()) {
            int[] move = directionMap.get(dir); // 해당 방향에 따른 이동값 가져오기
            int nextX = nowX + move[0]; // 이동 후 x 좌표 (2차원 좌표축)
            int nextY = nowY + move[1]; // 이동 후 y 좌표 (2차원 좌표축)
            
            // 이동한 좌표가 경계 범위를 벗어나는 경우 (-5 <= x, y <= 5)
            if (nextX < -5 || nextY < -5 || nextX > 5 || nextY > 5) {
                continue; // 다음 명령 탐색하도록 넘어감
            }
            
            // 현재 위치에서 다음 위치로 이동하는 경로를 문자열로 표현하는 과정
            String path1 = nowX + "," + nowY + "->" + nextX + "," + nextY; // 출발점 -> 도착점
            String path2 = nextX + "," + nextY + "->" + nowX + "," + nowY; // 도착점 -> 출발점
            
            // 양방향으로 추가해 중복 제거
            visitedSet.add(path1);
            visitedSet.add(path2);
            
            // 현재 위치를 다음 위치로 업데이트
            nowX = nextX;
            nowY = nextY;
        }
        
        // 결과값에 해시셋에 저장된 경로의 개수를 2로 나눈 값 저장 (양방향 저장으로 인해 중복됐으므로)
        answer = visitedSet.size() / 2;
        
        return answer;
    }
}