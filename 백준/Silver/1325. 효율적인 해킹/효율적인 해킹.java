import java.util.*;
import java.io.*;

public class Main {
	
	static class Computer {
		int idx;
		ArrayList<Computer> adj;
		
		public Computer(int idx) {
			this.idx = idx;
			this.adj = new ArrayList<>();
		}
	}
	
	static int N;
	static int M;
	static Computer[] comps;
	static boolean[] visited;
	static int[] answer;
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		comps = new Computer[N+1];
		answer = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			comps[i] = new Computer(i);
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			// 정점들 입력
			int a = Integer.parseInt(st.nextToken());	
			int b = Integer.parseInt(st.nextToken());
			
			// 단방향그래프
			// a가 b를 신뢰한다 (b를 해킹하면 a도 해킹 가능)
			comps[b].adj.add(comps[a]);
		}
		
		for(int i=1; i<=N; i++) {
			visited = new boolean[N+1];
			dfs(i, i);
		}
		
		// 최대값을 찾아내는 작업(최대 해킹 수)
		for(int i=1; i<=N; i++) {
			max = Math.max(max, answer[i]);
		}
				
				
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
		// 해당 컴퓨터 번호를 선택했을 때 최대로 해킹할 수 있는 것들을 찾아낸다
			if(max == answer[i]) {
				sb.append(i + " ");
			}
		}
		System.out.println(sb);
	}
	
	public static void dfs(int start, int now) {
		visited[now] = true;
		
		for(Computer c: comps[now].adj) {
			if(!visited[c.idx]) {
				dfs(start, c.idx);
				answer[start]++;
			}
		}
	}

}