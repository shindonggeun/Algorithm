import java.util.*;

class Solution {
    
    static boolean[] visited;
    static int maxDungeonCount;
    
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        
        visited = new boolean[dungeons.length];
        maxDungeonCount = Integer.MIN_VALUE;
        dfs(0, dungeons, k);
        
        answer = maxDungeonCount;
        
        return answer;
    }
    
    public void dfs(int count, int[][] dungeons, int current) {
        maxDungeonCount = Math.max(maxDungeonCount, count);
        
        for (int i=0; i<dungeons.length; i++) {
            if (!visited[i] && dungeons[i][0] <= current) {
                visited[i] = true;
                dfs(count + 1, dungeons, current - dungeons[i][1]);
                visited[i] = false;
            }
        }
    }
}