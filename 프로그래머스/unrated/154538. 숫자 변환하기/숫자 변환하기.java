import java.util.*;

class Solution {
    
    static int[] checked;
    static int maxValue = 100000000;

    public int solution(int x, int y, int n) {
        int answer = 0;
        checked = new int[maxValue + 1];
        answer = bfs(x, y, n);
        return answer;
    }
    
    public static int bfs(int start, int end, int n) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        checked[start] = 1;
        
        while(!queue.isEmpty()) {
            int number = queue.poll();
            
            if(number == end) {
                return checked[end] - 1;
            }
            
			if(number+n <= end && checked[number+n] == 0) {
				queue.add(number+n);
				checked[number+n] = checked[number] + 1;
			}
            
            if(number*2 <= end && checked[number*2] == 0) {
                queue.add(number*2);
                checked[number*2] = checked[number] + 1;
            }
            
            if(number*3 <= end && checked[number*3] == 0) {
                queue.add(number*3);
                checked[number*3] = checked[number] + 1;
            }
        }
        
        return -1;
    }
}