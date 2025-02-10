class Solution {
    
    static boolean[] visited; // 각 단어가 방문되었는지를 체크하는배열
    static int result; // 최소 변환 횟수를 저장하는 변수
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        visited = new boolean[words.length];
        
        result = 0; // 변환 횟수 0으로 초기화
        dfs(begin, target, words, 0); // 깊이 우선 탐색 메서드 수행
        
        answer = result; // 최종적으로 구해진 변환 횟수를 결과값에 저장
        
        return answer;
    }
    
    // 깊이 우선 탐색 메서드 (백트래킹)
    // start: 현재 변환된 단어
    // last: 목표 단어 (target)
    // words: 변환 가능한 단어 목록
    // cnt: 현재까지 변환된 횟수
    public static void dfs(String start, String last, String[] words, int cnt) {
        // 만약 현재 단어(start)가 목표 단어(last)와 같은 경우 (기저 조건)
        if(start.equals(last)) {
            result = cnt; // 현재까지의 변환 횟수를 저장
            return; // 메서드 종료
        }
        
        // 모든 words 배열을 순회하면서 변환 가능한 단어 찾는 과정
        for(int i=0; i<words.length; i++) {
            // 이미 방문한 단어인 경우
            if(visited[i]) {
                continue; // 넘어감
            }
            
            int count = 0; // 현재 단어(start)와 words[i]의 같은 문자 개수를 저장할 변수
            
            // 단어의 각 문자를 비교하여 몇 개의 문자가 같은지 확인하는 과정
            for(int j=0; j<start.length(); j++) {
                if(start.charAt(j) == words[i].charAt(j)) {
                    count++; // 같은 문자가 있는 경우 증가
                }
            }
            
            // 한 글자만 다를 경우에만 변환 가능 (즉, count 값이 start.length() - 1 이어야 함)
            if(count == start.length() - 1) {
                visited[i] = true;  // 해당 단어 방문했다고 체크
                dfs(words[i], last, words, cnt+1);  // 재귀를 통해 다음 단어로 DFS 수행 (변환 횟수 + 1)
                visited[i] = false; // 해당 단어 방문 여부 초기화 (백트래킹)
            }
        }
    }
}