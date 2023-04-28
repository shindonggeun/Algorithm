import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 정점의 수
	static int M;	// 간선의 수
	static int R;	// 시작 정점
	static ArrayList<Integer>[] graph;
	static int[] check;
	static int count;	// 방문순서를 나타내는 변수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N+1];
		check = new int[N+1];
		
		// graph의 index를 정점의 개수만큼 만들어준다
		for(int i=0; i<graph.length; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			
			graph[fromVertex].add(toVertex);
			graph[toVertex].add(fromVertex);
		}
		
		for(int i=1; i<graph.length; i++) {
			Collections.sort(graph[i]);
		}
		
		// 시작 정점도 순서에 포함이므로 count 초기값 1 할당
        count = 1;
        
        dfs(R);
        
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<check.length; i++) {
			sb.append(check[i]).append("\n");
		}
		System.out.print(sb);

	}
	
	public static void dfs(int vertex) {
		check[vertex] = count;	// 해당 정점 방문한거 체크하면서 동시에 해당 노드의 방문순서 저장
		
		// 현재 방문한 노드에 해당하는 리스트에 담겨있는 값들 하나씩 뽑아내기
		for(int i: graph[vertex]) {
			// 방문안한 노드인 경우
			if(check[i] == 0) {
				count++;
				dfs(i);	// 그 노드에 해당하는 리스트들 탐색할 수 있도록 재귀 호출
			}
		}
	}

}
