import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int M;
	static int[] arr;
	static int[] output;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		output = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);	// 오름차순 정렬
		dfs(0, 0);
		System.out.println(sb);
	}
	
	// 조합 메서드 이용
	public static void dfs(int depth, int idx) {
		if(depth == M) {
			for(int i=0; i<M; i++) {
				sb.append(output[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=idx; i<N; i++) {
			output[depth] = arr[i];
			dfs(depth+1, i+1);
		}
	}

}