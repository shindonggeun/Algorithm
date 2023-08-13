import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static int[] arr;	// 선택하고자 하는 대상 배열 (즉, 처음 정수값들을 저장할 배열)
	static int[] output;	// 대상 숫자를 담아올 배열 (정수값들을 순열로 나타낼 배열)
	static boolean[] visited; // 방문배열 (순열에서는 필수)
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];	// 선택하고자 하는 대상 배열은 길이가 N
		visited = new boolean[N];	// 방문배열 생성
		output = new int[M];	// 대상 숫자를 담아올 배열은 길이가 M
		sb = new StringBuilder();
		
		// 대상 집합에 숫자 저장해주기
		for(int i=0; i<N; i++) {
			arr[i] = i+1;
		}
		
		backTracking(0);	// 백트래킹 실시
		System.out.print(sb);    // StringBuilder에 저장된 순열들 출력
	}
    
	// 백트래킹 (순열 메서드)
	public static void backTracking(int depth) {
		// 해당 깊이가 선택 횟수(즉 수열 길이 M)와 같아지면 결과를 출력하고 재귀 종료 (종료조건)
		if(depth == M) {
			for(int i=0; i<M; i++) {
				sb.append(output[i]).append(" ");
			}
			sb.append("\n");
			return;	// 메서드 종료
		}
		
		for(int i=0; i<N; i++) {
			// 만약 해당 정수를 방문(선택)하지 않은경우
			if (!visited[i]) {
				visited[i] = true; // 해당 정수값을 방문상태(선택했다)로 변경
				output[depth] = arr[i]; // 해당 깊이(depth)를 index로 하여 선택한 정수값을 저장
				backTracking(depth + 1); // 다음 정수값 선택을 위해 depth를 1 증가시키면서 재귀 호출함
				
				// 해당 정수값 방문(선택)이 끝나고 돌아오면 해당 정수값를 방문(선택)하지 않은 상태로 변경(백트래킹)
				visited[i] = false;
			}
		}
	}

}
