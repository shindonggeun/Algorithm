import java.util.*;

class Solution {
    static boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[computers.length];
        
        for(int i=0; i<computers.length; i++) {
            if(!visited[i]) {
                answer++;
                dfs(i, computers);
            }
        }
        return answer;
    }
    
    public static void dfs(int node, int[][] computers) {
        visited[node] = true;
        
        for(int i=0; i<computers.length; i++) {
            if(visited[i] == false && computers[node][i] == 1 && node != i) {
                dfs(i, computers);
            }
        }
    }
}