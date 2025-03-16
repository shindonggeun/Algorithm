import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 1부터 N까지의 자연수 (개수)
	static int M; // 길이가 M인 수열을 만들기 위한 변수
	static int[] rePermutation; // 현재 생성 중인 수열을 저장하는 배열 (중복 순열)
	static StringBuilder sb; // 결과를 한번에 출력하기 위한 StringBuilder

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		rePermutation = new int[M]; // [0] ~ [M-1]
		sb = new StringBuilder();
		
		generateRePermutation(0); // 중복 순열을 생성하기 위한 백트래킹 메서드 호출
		
		System.out.print(sb); // 결과 출력
	}
	
	// 중복순열을 만들기 위한 백트래킹 메서드
	public static void generateRePermutation(int depth) {
		// 현재 선택한 숫자 개수가 M개인 경우 (즉, M개의 숫자를 모두 선택한 경우) (기저 조건)
		if (depth == M) {
			// 선택된 숫자를 출력하기 위해 저장하는 과정
			for (int i=0; i<M; i++) {
				sb.append(rePermutation[i]).append(" ");
			}
			sb.append("\n");
			return; // 메서드 종료
		}
		
		// 1부터 M까지의 숫자를 선택하는 과정
		for (int i=0; i<N; i++) {
			rePermutation[depth] = i + 1; // 현재 depth 위치에 숫자 저장
			generateRePermutation(depth + 1); // 다음 숫자를 선택하기 위해 재귀 호출
		}
	}

}