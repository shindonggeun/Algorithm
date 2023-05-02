import java.util.*;

class Solution {
    boolean[] visited;
    ArrayList<String> allRoute;
    
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        visited = new boolean[tickets.length];  
        allRoute = new ArrayList<>();
        dfs("ICN", "ICN", tickets, 0);
        
        Collections.sort(allRoute);
        answer = allRoute.get(0).split(" ");
        
        return answer;
    }
    // 사실상 백트래킹 이용해야함
    public void dfs(String start, String route, String[][] tickets, int cnt){
        // 주어진 티켓 다 사용한 경우 재귀호출 종료
        if(cnt == tickets.length){
            allRoute.add(route);    // 해당 루트 다 저장함
            return; 
        }
        
        for(int i=0; i<tickets.length; i++){
            if(start.equals(tickets[i][0]) && !visited[i]){
                visited[i] = true;  // 방문처리
                dfs(tickets[i][1], route+" "+tickets[i][1], tickets, cnt+1);
                visited[i] = false; // 미방문처리
            }
        }
    }
    
}