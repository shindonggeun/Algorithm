import java.util.*;
import java.io.*;

public class Main {
	
	static int[] checked;
	static int[] beforeLocation;
	static int count;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int start = Integer.parseInt(st.nextToken());	// 수빈이의 위치
		int end = Integer.parseInt(st.nextToken());		// 수빈이 동생의 위치
		checked = new int[100001];	// (0 ~ 100000)
		beforeLocation = new int[100001];
		
		bfs(start, end);
		
		Stack<Integer> stack = new Stack<>();
		stack.push(end);
		int index = end;
		
		while(index != start) {
			stack.push(beforeLocation[index]);
			index = beforeLocation[index];
		}
		
		sb.append((checked[end] - 1)).append("\n");
		while(!stack.isEmpty()) {
			sb.append(stack.pop() + " ");
		}
		System.out.print(sb);
	}
	
	public static void bfs(int start, int end) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		checked[start] = 1;
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			
			if(now == end) {
				break;
			}
			
			// 3가지 방법 탐색
			for(int i=0; i<3; i++) {
				int next = 0;
				
				if(i == 0) {
					next = now + 1;
				}
				else if(i == 1) {
					next = now - 1;
				}
				else if(i == 2) {
					next = now * 2;
				}
				
				// 이동한 위치가 범위를 벗어난 경우 (0 ~ 100000 이외의 위치인 경우) 
				if(next < 0 || next > 100000) {
					continue;
				}
				
				// 탐색한 위치가 방문하지 않은 위치인 경우
				if(checked[next] == 0) {
					queue.add(next);
					checked[next] = checked[now] + 1;
					beforeLocation[next] = now;	// 해당 탐색한 위치에 직전위치정보를 저장해준다
				}
			}
			
		}
	}

}
