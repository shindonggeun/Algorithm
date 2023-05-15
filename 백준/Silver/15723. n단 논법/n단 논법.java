import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	static boolean find;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 간선의 개수
		
		// graph의 index를 정점의 개수만큼 만들어준다 (a: 0 ... z: 25)
		for(int i=0; i<26; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0; i<N; i++) {
			String input = br.readLine();
			String[] inputArr = input.split(" is ");
			
			// 단방향 간선
			char from = inputArr[0].charAt(0);
			char to = inputArr[1].charAt(0);
			
			int fromVertex = from - 'a';	
			int toVertex = to - 'a';
			
			graph.get(fromVertex).add(toVertex);
		}
		
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<M; i++) {
			String input = br.readLine();
			String[] inputArr = input.split(" is ");
			
			// 단방향 간선
			char from = inputArr[0].charAt(0);
			char to = inputArr[1].charAt(0);
						
			int fromVertex = from - 'a';	
			int toVertex = to - 'a';
			visited = new boolean[26];
			find = false;
			dfs(fromVertex, toVertex);
			
			if(find) {
				sb.append("T").append("\n");
			}
			else {
				sb.append("F").append("\n");
			}
		}
		System.out.print(sb);
	}
	
	public static void dfs(int vertex, int end) {
		if(vertex == end) {
			find = true;
			return;
		}
		visited[vertex] = true;	// 해당 정점 방문한거 체크
		
		// 현재 방문한 노드에 해당하는 리스트에 담겨있는 값들 하나씩 뽑아내기
		for(int i: graph.get(vertex)) {
			// 방문안한 노드인 경우
			if(!visited[i]) {
				dfs(i, end);	// 그 노드에 해당하는 리스트들 탐색할 수 있도록 재귀 호출
			}
		}
		
	}

}
