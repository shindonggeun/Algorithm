import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 1부터 N까지의 자연수 (개수)
	static int M; // 1부터 N까지의 자연수 중 중복 없이 숫자들을 선택한 개수
	static int[] arr; // 선택하고자 하는 대상 배열 (즉, 처음 정수값들을 저장할 배열)
	static int[] combination; // 현재 선택된 조합을 저장할 배열
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N]; // [0] ~ [N-1]
		combination = new int[M]; // [0] ~ [M-1]
		
		for (int i=0; i<N; i++) {
			arr[i] = i + 1;
		}
		
		sb = new StringBuilder();
		
		generateCombination(0, 0); // 조합 만들기 시작
		
		System.out.print(sb);
	}
	
	// 백트래킹 알고리즘을 이용한 조합 메서드
	// depth: 현재 선택한 수의 개수 (combination 배열에서 채운 깊이)
	// idx: 다음에 탐색할 시작 인덱스 (arr에서 선택할 수 있는 시작 위치)
	public static void generateCombination(int depth, int idx) {
		// M개의 수를 모두 선택한 경우 (기저 조건)
		if (depth == M) {
			// 선택된 조합 출력
			for (int i=0; i<M; i++) {
				sb.append(combination[i]).append(" ");
			}
			sb.append("\n");
			return; // 메서드 종료
		}
		
		// idx부터 N-1까지 반복하며 조합 구성
		for (int i=idx; i<N; i++) {
			combination[depth] = arr[i]; // 현재 위치 i의 값을 조합에 저장
			// 다음 depth로 들어가고, 다음 탐색 시작 인덱스는 i+1로 설정 -> arr[i]보다 작은 수가 다시 선택되지 않도록 함
			generateCombination(depth + 1, i + 1);
		}
	}

}