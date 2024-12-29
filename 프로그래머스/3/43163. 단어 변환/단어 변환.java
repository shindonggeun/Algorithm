class Solution {
    static boolean[] visited;
    static int result = 0;
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        visited = new boolean[words.length];
        dfs(begin, target, words, 0);
        answer = result;
        
        return answer;
    }
    
    // dfs 알고리즘 이용
    public static void dfs(String start, String last, String[] words, int cnt) {
        if(start.equals(last)) {
            result = cnt;
            return;
        }
        
        // 각 인덱스의 문자열을 탐색해줌
        for(int i=0; i<words.length; i++) {
            // 이미 탐색한 경우 넘어감
            if(visited[i]) {
                continue;
            }
            
            int count = 0;
            // 같은 글자 몇개인지 세기
            for(int j=0; j<start.length(); j++) {
                if(start.charAt(j) == words[i].charAt(j)) {
                    count++;
                }
            }
            // 한글자빼고 모두 같은 경우
            if(count == start.length() - 1) {
                visited[i] = true;  // 방문한걸로 침
                dfs(words[i], last, words, cnt+1);  // 재귀를 통해 다음 문자열로 이동
                visited[i] = false; // 다시 방문안한걸로 만들어줌
            }
            
            
        }
    }
}