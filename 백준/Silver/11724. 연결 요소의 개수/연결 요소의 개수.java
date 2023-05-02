import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 정점의 개수
	static int M; 	// 간선의 개수
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N+1];
		int count = 0;
		
		// graph의 index를 정점의 개수만큼 만들어준다
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			
			// 양방향 그래프
			graph.get(fromVertex).add(toVertex);
			graph.get(toVertex).add(fromVertex);
		}
		
		for(int i=1; i<graph.size(); i++) {
			Collections.sort(graph.get(i));
		}
		
		// 이부분이 가장 중요! (각 방문 안된 정점마다 dfs 탐색한다!)
		for(int i=1; i<=N; i++) {
            if(!visited[i]) {
                count++;
                dfs(i);
            }
        }
		System.out.println(count);
		
	}
	
	public static void  dfs(int vertex) {
		visited[vertex] = true;	// 해당 정점 방문한거 체크하면서 동시에 해당 노드 방문처리
		
		// 현재 방문한 노드에 해당하는 리스트에 담겨있는 값들 하나씩 뽑아내기
		for(int i: graph.get(vertex)) {
			// 방문안한 노드인 경우
			if(!visited[i]) {
				dfs(i);	// 그 노드에 해당하는 리스트들 탐색할 수 있도록 재귀 호출
			}
		}
		
	}

}
