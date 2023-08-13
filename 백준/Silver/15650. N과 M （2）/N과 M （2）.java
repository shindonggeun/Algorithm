import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static int[] arr;	// 선택하고자 하는 대상 배열 (즉, 처음 정수값들을 저장할 배열)
	static int[] output;	// 대상 숫자를 담아올 배열 (정수값들을 조합으로 나타낼 배열)
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];	// 선택하고자 하는 대상 배열은 길이가 N
		output = new int[M];	// 대상 숫자를 담아올 배열은 길이가 M
		sb = new StringBuilder();
		
		// 대상 집합에 숫자 저장해주기
		for(int i=0; i<N; i++) {
			arr[i] = i+1;
		}
		
		backTracking(0, 0);	// 백트래킹 실시
		System.out.print(sb);
	}
	
	// 백트래킹 (조합 메서드)
	public static void backTracking(int depth, int idx) {
		// 해당 깊이가 선택 횟수(즉, 수열 길이 M)과 같아지면 결과를 출력하고 재귀 종료 (종료조건)
		if(depth == M) {
			for(int i=0; i<M; i++) {
				sb.append(output[i]).append(" ");
			}
			sb.append("\n");
			return;	// 메서드 종료
		}
		
		// idx부터 시작인거 주의!!! (순열메서드와 다른점)
		// idx는 조합을 시도할 원소의 시작 인덱스라고 보면 된다
		for(int i=idx; i<N; i++) {
			output[depth] = arr[i];	// 해당 깊이(depth)를 index로 하여 선택한 정수값을 저장
			backTracking(depth+1, i+1);	// 다음 정수값 선택을 위해 depth를 1 증가시키면서 재귀 호출함
		}
	}

}
