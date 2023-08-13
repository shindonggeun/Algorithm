import java.util.*;
import java.io.*;

public class Main {

	static boolean[] visited;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N];	// 방문배열 선언
		arr = new int[M];			// 해당 숫자들 저장할 변수 (M 길이)
		
		backTracking(N, M, 0);
		System.out.print(sb);
	}
	
	// 백트래킹 메서드 (가지치기)
	// 순열 메서드
	public static void backTracking(int N, int M, int depth) {
		// 해당 깊이가 조건에 만족된 경우(즉, 수열의 길이가 M이 되면)
		if(depth == M) {
			for(int val: arr) {
				sb.append(val).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=0; i<N; i++) {
			// 만약 해당 수를 선택하지 않은 경우
			if(!visited[i]) {
				visited[i] = true;	// 방문처리 해준 뒤
				arr[depth] = i+1;	// 배열에 해당 수를 저장한 뒤
				backTracking(N, M, depth+1);	// backTracking() 메서드 재귀 호출 (depth 증가해줌)
				visited[i] = false;	// 방문처리 해제 
			}
		}
	}

}
