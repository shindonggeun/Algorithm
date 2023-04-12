import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int S;
	static int[] arr;
	static int[] output;
	static int count = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		output = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 모든 조합 다 뽑아내기
		for(int i=0; i<N; i++) {
			dfs(0, i+1, 0);
		}
		System.out.println(count);
		
	}
	
	// 조합 메서드
	public static void dfs(int depth, int limit ,int idx) {
		// 해당 깊이가 선택횟수와 같아지면 결과를 출력하고 재귀 종료
		if(depth == limit) {
			int sum = 0;
			// 해당 부분수열을 다 더해줌
			for(int i=0; i<limit; i++) {
				sum += output[i];
			}
			// 부분수열 다 더했을 때 S인 경우 경우의 수 증가
			if(sum == S) {
				count++;
			}
			return;
		}
		
		for(int i=idx; i<N; i++) {
			output[depth] = arr[i];
			dfs(depth+1, limit, i+1);
		}
	}

}