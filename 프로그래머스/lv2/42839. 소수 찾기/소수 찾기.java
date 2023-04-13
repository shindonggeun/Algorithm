import java.util.*;

class Solution {
    static int[] arr;
    static boolean[] visited;
    static int N;
    static HashSet<Integer> set;
    
    public int solution(String numbers) {
        int answer = 0;
        
        arr = new int[numbers.length()];
        visited = new boolean[numbers.length()];
        N = numbers.length();
        set = new HashSet<>();
        
        for(int i=0; i<N; i++) {
            arr[i] = numbers.charAt(i) - '0';
        }
        
        for(int i=1; i<=N; i++) {
            dfs(0, 0, i);
        }
        
        for(int i: set) {
            if(isPrime(i)) {
                answer++;
            }
        }
        
        return answer;
    }
    
    // 순열 메서드 이용
    public static void dfs(int depth, int num, int count) {
        if(depth == count) {
            set.add(num);
            return;
        }
        
        for(int i=0; i<N; i++) {
            // 이미 방문한 경우 넘어감
            if(visited[i]) {
                continue;
            }
            // 맨 앞자리가 0인경우 넘어감
            if(depth == 0 && arr[i] == 0) {
                continue;
            }
            
            visited[i] = true;
            dfs(depth+1, num * 10 + arr[i], count);
            visited[i] = false;
        }
    }
    // 소수인지 판별하는 메서드
    public static boolean isPrime(int number) {
        boolean find = true;
        if(number == 1) {
            find = false;
            return find;
        }
        
        for(int i=2; i<=number/2; i++) {
            if(number % i == 0) {
                find = false;
                break;
            }
        }
        return find;
    }
}