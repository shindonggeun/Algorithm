import java.util.*;
import java.io.*;

public class Main {
	
	public static boolean[] visit;
	public static int[] arr;
	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		visit = new boolean[N];
		arr = new int[M];
		dfs(N, M, 0);
		
		System.out.print(sb);
		

	}
	
	public static void dfs(int N, int M, int depth) {
		
		if(depth == M) {
			for(int val: arr) {
				sb.append(val).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=0; i<N; i++) {
			// 만약 해당 노드(값)을 방문하지 않은경우
			if(!visit[i]) {
				visit[i] = true;		// 해당 노드를 방문상태로 변경
				arr[depth] = i+1;		// 해당 깊이를 index로 하여 i+1 값 저장
				dfs(N, M, depth + 1);	// 다음 자식 노드 방문을 위해 depth를 1 증가시키면서 재귀 호출함
				
				// 자식노드 방문이 끝나고 돌아오면 방문노드를 방문하지 않은 상태로 변경
				visit[i] = false;
			}
		}
		return;
	}

}