import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int[] numArr;
	static boolean[] visited;
	static List<Integer> resultList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		numArr = new int[N+1];
		visited = new boolean[N+1];
		
		for (int i=1; i<=N; i++) {
			numArr[i] = Integer.parseInt(br.readLine());
		}
		
		resultList = new ArrayList<>();
		
		for (int i=1; i<=N; i++) {
			visited[i] = true;
			dfs(i, i);
			visited[i] = false;
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(resultList.size()).append("\n");
		
		for (int result: resultList) {
			sb.append(result).append("\n");
		}
		
		System.out.print(sb);
	}
	
	public static void dfs(int now, int target) {
		if (numArr[now] == target) {
			resultList.add(target);
		}
		
		if (!visited[numArr[now]]) {
			visited[numArr[now]] = true;
			dfs(numArr[now], target);
			visited[numArr[now]] = false;
		}
	}

}
