import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int[] arr;
	static boolean[] visited;
	static int numA;
	static int numB;
	static int min;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String inputA = st.nextToken();
		String inputB = st.nextToken();
		
		arr = new int[inputA.length()];
		visited = new boolean[inputA.length()];
		
		for(int i=0; i<inputA.length(); i++) {
			arr[i] = inputA.charAt(i) - '0';
		}
		
		numA = Integer.parseInt(inputA);
		numB = Integer.parseInt(inputB);
		min = -1;
		
		
		dfs(0, 0);
		System.out.println(min);
		
	}
	// 순열 메서드
	public static void dfs(int depth, int num) {
		if(depth == arr.length) {
			min = Math.max(min, num);
			return;
		}
		
		for(int i=0; i<arr.length; i++) {
			// 이미 방문했으면 넘어감
			if(visited[i]) {
				continue;
			}
			// 맨 앞자리가 0이면 넘어감
			if(depth == 0 && arr[i] == 0) {
				continue;
			}
			// 다음 순열에 해당하는 값이 B값보다 큰 경우 넘어감
			if(num * 10 + arr[i] > numB) {
				continue;
			}
			
			visited[i] = true;
			dfs(depth+1, num * 10 + arr[i]);
			visited[i] = false;
		}
	}

}