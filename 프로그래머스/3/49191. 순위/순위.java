import java.util.*;

class Solution {
    
    static ArrayList<ArrayList<Integer>> winerList;
    static ArrayList<ArrayList<Integer>> loserList;
    
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        winerList = new ArrayList<>();
        loserList = new ArrayList<>();
        
        for (int i=0; i<=n; i++) {
            winerList.add(new ArrayList<>());
            loserList.add(new ArrayList<>());
        }
        
        for (int[] result: results) {
            int winer = result[0];
            int loser = result[1];
            
            winerList.get(loser).add(winer);
            loserList.get(winer).add(loser);
        }
        
        int count = 0;
        for (int i=1; i<=n; i++) {
            if (bfs(i, n)) {
                count++;
            }
        }
        
        answer = count;
        
        return answer;
    }
    
    public boolean bfs(int player, int n) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n+1];
        
        queue.add(player);
        visited[player] = true;
        
        while (!queue.isEmpty()) {
            int now = queue.poll();
            
            for (int next: winerList.get(now)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
        
        queue.add(player);
        
        while (!queue.isEmpty()) {
            int now = queue.poll();
            
            for (int next: loserList.get(now)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
        
        for (int i=1; i<=n; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        
        return true;
    }
}