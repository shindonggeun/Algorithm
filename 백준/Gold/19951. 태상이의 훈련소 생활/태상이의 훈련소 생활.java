import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 연병장의 크기
	static int M; // 조교의 수
	static int[] heightArr; // 초기 연병장의 각 칸의 높이를 저장하는 배열
	static int[] diffArr; // 차분 배열 이용 (누적합 응용) (각 칸의 높이 변화량)
	static int[] result; // 차분 배열을 누적합한 결과 배열 (즉, 각 칸의 최종 높이를 저장하는 배열)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		heightArr = new int[N+1]; // [1] ~ [N]
		diffArr = new int[N+2]; // 차분 배열의 크기는 N+2로 초기화 ([1] ~ [N+1])
		
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			heightArr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 시작 칸 입력
			int b = Integer.parseInt(st.nextToken()); // 종료 칸 입력
			int k = Integer.parseInt(st.nextToken()); // 높이 k 입력
			
			diffArr[a] += k; // 차분 배열 해당 시작칸에 높이 k 만큼 더해줌
			diffArr[b+1] -= k; // 차분 배열 종료 칸 다음 칸에 높이 k 만큼 빼줌
		}
		
		result = new int[N+1]; // [1] ~ [N]
		
		// 차분배열에서 누적합을 이용하여 최종 높이 계산
		int sum = 0; // 누적합 계산을 위한 변수
		// 1번 칸부터 N번 칸까지 순회
		for (int i=1; i<=N; i++) {
			sum += diffArr[i]; // 해당 칸의 차분 배열에 저장된 값 누적합에 더해줌
			result[i] = heightArr[i] + sum; // 해당 칸의 초기 높이 + 지금까지 누적합 된것을 저장
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=N; i++) {
			sb.append(result[i]).append(" ");
		}
		
		System.out.println(sb);
	}

}