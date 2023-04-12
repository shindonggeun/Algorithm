import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int M;
	static int[] arr;		// 선택하고자 하는 숫자 집합을 담은 배열
	static int[] output;	// 대상 숫자를 담아줄 배열
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		output = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = i+1;
		}
		
		sb = new StringBuilder();
		dfs(0);
		System.out.println(sb);

	}
	
	public static void dfs(int depth) {
		// 해당 깊이가 만족되면 출력하고 메서드 종료
		if(depth == M) {
			for(int i=0; i<M; i++) {
				sb.append(output[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		// 대상 집합을 순회하며 숫자 하나씩 선택
		// 일반 순열 알고리즘에서 중복선택을 방지하기 위해 만들었던 visited배열이 사라진것을 확인할 수 있음
		for(int i=0; i<N; i++) {
			output[depth] = arr[i];
			dfs(depth + 1);
		}
	}

}