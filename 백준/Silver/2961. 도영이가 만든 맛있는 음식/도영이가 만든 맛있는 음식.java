import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int[] taste1;	// 신맛을 담은 배열
	static int[] taste2;	// 쓴맛을 담은 배열
	static int[] output1;	// 신맛의 재료 조합 결과
	static int[] output2;	// 쓴맛의 재료 조합 결과
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		taste1 = new int[N];
		taste2 = new int[N];
		output1 = new int[N];
		output2 = new int[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			taste1[i] = Integer.parseInt(st.nextToken());
			taste2[i] = Integer.parseInt(st.nextToken());
		}
		
		// 모든 조합 뽑아내기
		for(int i=0; i<N; i++) {
			dfs(0, i+1, 0);
		}
		
		System.out.println(min);
	}
	
	// 조합 메서드
	public static void dfs(int depth, int count, int idx) {
		// 해당 조합 개수가 충족되면 재귀 종료
		if(depth == count) {
			int result1 = 1;
			int result2 = 0;
			for(int i=0; i<count; i++) {
				result1*=output1[i];	// 신맛들의 재료 조합은 곱해서 결과값 뽑아냄
				result2+=output2[i];	// 쓴맛들의 재료 조합은 더해서 결과값 뽑아냄
			}
			min = Math.min(min, Math.abs(result1 - result2));	// 신맛과 쓴맛의 차이의 최소값 저장
			return;
		}
		
		for(int i=idx; i<N; i++) {
			output1[depth] = taste1[i];
			output2[depth] = taste2[i];
			dfs(depth+1, count, i+1);
		}
	}

}
