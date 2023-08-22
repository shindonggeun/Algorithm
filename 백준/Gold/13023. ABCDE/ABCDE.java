import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 사람의 수 (정점의 수)
	static int M;	// 친구 관계의 수 (간선의 수)
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static boolean[] visited;	// 방문배열
	static boolean friendRelation;	// 친구관계가 존재하는지 여부를 판단해주는 변수
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 그래프에서 정점(친구들) 만들어주기
		for(int i=0; i<N; i++) {
			graph.add(new ArrayList<>());
		}
	
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			
			// 친구 관계 연결해주기 (양방향 간선)
			graph.get(fromVertex).add(toVertex);
			graph.get(toVertex).add(fromVertex);
		}
		friendRelation = false;	
		
		// 친구번호 0번부터 N-1번까지 탐색
		for(int i=0; i<N; i++) {
			visited = new boolean[N];	// 방문배열 초기화
			dfs(0, i);	// 시작정점부터 깊이우선탐색 시작 (depth는 0)
		}
		
		// 친구관계 성립하면
		if(friendRelation) {
			System.out.println(1);	
		}
		// 친구관계 성립 안되면
		else {
			System.out.println(0);
		}
		
	}
	
	// 깊이우선탐색 메서드
	public static void dfs(int depth, int vertex) {
		visited[vertex] = true;	// 해당 정점(친구) 방문 처리
		
		// 깊이가 4가 된 경우 (즉, 문제에서 주어진 친구관계 성립 된 경우) (종료 조건)
		if(depth == 4) {
			friendRelation = true;	// 친구관계 찾음
			return;	// 메서드 종료
		}
		
		// 친구관계 찾았으면 더이상 탐색할 필요도 없으므로
		if(friendRelation) {
			return;	// 메서드 종료
		}
		
		for(int i: graph.get(vertex)) {
			// 해당 정점이 방문처리 안된 경우
			if(!visited[i]) {
				visited[i] = true;	// 방문처리 해준 뒤
				dfs(depth+1, i);	// 깊이우선탐색 실시
				visited[i] = false;	// 방문처리 해제 (백트래킹)
			}
		}
		
		
	}

}
