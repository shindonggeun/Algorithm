import java.util.*;
import java.io.*;

public class Main {

	static int[] arr;
	static boolean[] visited;
	static int X;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String num = st.nextToken();
		X = Integer.parseInt(num);
		
		arr = new int[num.length()];
		visited = new boolean[num.length()];
		
		for(int i=0; i<num.length(); i++) {
			arr[i] = num.charAt(i) - '0';
		}
		
		dfs(0, 0);
		
		// 위의 수의 구성을 다 돌려봤는데 나온 수들중 가장 작은값을 만들 수 없는 경우
		if(min == Integer.MAX_VALUE) {
			System.out.println(0);	// 0 출력
		}
		else {
			System.out.println(min);
		}
	}
	
	// 순열 메서드 이용
	public static void dfs(int depth, int n) {
		// 수의 구성이 같아 진 경우(즉 자리수 같아질 때가 메서드 종료조건)
		if(depth == arr.length) {
			// 정수 X 보다 큰 수 일때
			if(X < n) {
				min = Math.min(min, n);	// 그 나온 수들중 가장 작은값 저장하기
			}
			return;
		}
		
		for(int i=0; i<arr.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				dfs(depth+1, n*10+arr[i]);
				visited[i] = false;
			}
		}
	}

}
