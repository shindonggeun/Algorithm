import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int M;
	static int[] arr;	// 선택하고자 하는 대상 집합(숫자들)
	static int[] output;	// 대상 숫자를 담아올 배열
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		output = new int[N];
		sb = new StringBuilder();
		
		for(int i=0; i<N; i++) {
			arr[i] = i+1;
		}
		
		dfs(0, 0);
		System.out.print(sb);
	}
	
	// 조합 메서드(depth는 선택 횟수, idx는 다음 대상을 선택할때 집합에서 탐색하는 인덱스)
	public static void dfs(int depth, int idx) {
		// 해당 깊이가 선택횟수와 같아지면 결과를 출력하고 재귀 종료
		if(depth == M) {
			for(int i=0; i<M; i++) {
				sb.append(output[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		// 대상 집합을 주어진 인덱스부터 순회하며 숫자를 하나 선택함
		for(int i=idx; i<N; i++) {
			output[depth] = arr[i];
			// 자신 이전의 수를 중복선택하지 않도록 인덱스를 +1하여 재귀호출함
			dfs(depth+1, i+1);	
		}
	}

}