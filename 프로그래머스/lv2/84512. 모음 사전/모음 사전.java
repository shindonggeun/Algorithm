import java.util.*;

class Solution {
    static List<String> list;
    static int maxLength = 5;
    static String[] arr = {"A", "E", "I", "O", "U"};
    
    public int solution(String word) {
        int answer = 0;
        list = new ArrayList<>();
        
        dfs("", 0); // dfs() 시작
        
        // 선형탐색
        for(int i=0; i<list.size(); i++) {
            if(list.get(i).equals(word)) {
                answer = i;
                break;
            }
        }
        
        return answer;
    }
    
    public static void dfs(String str, int depth) {
        // 깊이 5 넘어가면 탈출조건
        if(depth > 5) {
            return;
        }
        
        list.add(str);
        
        for(int i=0; i<arr.length; i++) {
            dfs(str+arr[i], depth + 1);
        }
    }
}