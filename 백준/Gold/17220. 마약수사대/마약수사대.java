import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 마약 공급책의 수 (정점의 수)
	static int M;	// 마약 공급책의 관계의 수 (간선의 수)
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static int[] tempHeader;	// 마약을 공급받는 곳의 수를 저장해주는 배열 (즉 해당 정점과 연결된 상단 노드의 수)
	static Set<Integer> set = new HashSet<>();	// 마약 최상단 공급책을 저장하는 HashSet
	static boolean[] visited;	// 정점 방문여부 배열
	static int count = 0;	// 마약 공급을 받고있는 정점의 수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		tempHeader = new int[N];
		visited = new boolean[N];
		
		// graph의 index를 정점의 개수만큼 만들어준다 (A: 0 ... Z: 25)
		for(int i=0; i<N; i++) {
			graph.add(new ArrayList<>());
		}
		
		// 간선 연결해주기
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			char from = st.nextToken().charAt(0);
			char to = st.nextToken().charAt(0);
			
			int fromVertex = from - 'A';	
			int toVertex = to - 'A';
			
			// 단방향 간선 연결
			graph.get(fromVertex).add(toVertex);
			tempHeader[toVertex]++;	// 해당 정점과 연결된 상단 노드의 수 증가
		}
		
		// 최상단 노드 찾아내는 작업 (최상단 마약 공급책이 2개 이상인 경우가 있으므로 이 작업 해줘야한다!)
		for(int i=0; i<N; i++) {
			// 최상단 노드인 경우 (최상단 노드는 연결된 상단노드가 없으므로)
			if(tempHeader[i] == 0) {
				set.add(i);	// HashSet에 추가해줌
			}
		}		
		
		st = new StringTokenizer(br.readLine());
		int arrestNum = Integer.parseInt(st.nextToken());	// 체포한 수
		
		for(int i=0; i<arrestNum; i++) {
			char arrestAlpabet = st.nextToken().charAt(0);
			int fromVertex = arrestAlpabet - 'A';
			
			graph.get(fromVertex).clear();	// 해당 마약공급책과 연결된 정점들 싹 제거해주기
			// 체포한 마약 공급책이 최상단 마약공급책 명단에 없는 경우
			if(!set.contains(fromVertex)) {
				// 각 정점들(마약공급책)에서 체포한 마약공급책(정점)들 삭제해주기
				for(int j=0; j<graph.size(); j++) {
					graph.get(j).remove(Integer.valueOf(fromVertex));
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			// 최상단 마약 공급책인 경우에만 깊이우선탐색 실시
			if(tempHeader[i] == 0) {
				dfs(i);
			}
		}
		System.out.println(count);	// 여전히 마약을 공급받고 있는 정점들의 개수 출력
	}
	
	public static void dfs(int vertex) {
		visited[vertex] = true;	// 해당 정점 방문한거 체크
		
		// 현재 방문한 노드에 해당하는 리스트에 담겨있는 값들 하나씩 뽑아내기
		for(int i: graph.get(vertex)) {
			// 방문안한 노드인 경우
			if(!visited[i]) {
				dfs(i);	// 그 노드에 해당하는 리스트들 탐색할 수 있도록 재귀 호출
				count++;
			}
		}
	}

}
