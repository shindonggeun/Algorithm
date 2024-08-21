import java.util.*;
import java.io.*;

public class Main {

	static int N; // 1부터 N까지의 자연수 (개수)
	static int M; // 1부터 N까지의 자연수 중 중복 없이 숫자들을 선택한 개수
	static boolean[] visited; // 해당 숫자들의 방문 여부를 나타내는 배열 (순열에서 필요)
	static int[] permutation; // 순열을 표현할 배열
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N]; // [0] ~ [N]
		permutation = new int[M]; // [0] ~ [M-1]
		
		sb = new StringBuilder();
		
		// 순열을 만들기 위해 해당 메서드 호출
		generatePermutation(0);
		
		System.out.print(sb);
	}
	
	// 순열을 만드는 메서드 (백트래킹)
	public static void generatePermutation(int depth) {
		// 해당 깊이가 선택 횟수(즉, 선택한 숫자 개수 M)과 같아지는 경우 (기저 조건)
		if (depth == M) {
			// 해당 순열 결과를 출력하게끔 StringBuilder에 저장하는 과정
			for (int i=0; i<M; i++) {
				sb.append(permutation[i]).append(" ");
			}
			sb.append("\n"); // 줄바꿈
			return; // 메서드 종료
		}
		
		for (int i=0; i<N; i++) {
			// 만약 해당 숫자들 방문(선택)하지 않은 경우
			if (!visited[i]) {
				visited[i] = true; // 해당 정수값 방문 상태로 표시 (선택)
				permutation[depth] = i + 1; // 해당 깊이(depth)를 인덱스로 하여 선택한 정수값 저장
				generatePermutation(depth + 1); // 다음 정수값 선택을 위해 깊이를 1 증가시키면서 재귀 호출
				visited[i] = false; // 해당 정수값 방문 상태 해제 (선택 해제)
			}
		}
	}

}