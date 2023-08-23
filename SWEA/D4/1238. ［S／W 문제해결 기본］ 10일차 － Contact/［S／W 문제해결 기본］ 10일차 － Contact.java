import java.util.*;
import java.io.*;

public class Solution {
	
	static ArrayList<ArrayList<Integer>> graph;	// 정점들 연결할 그래프
	static boolean[] visited;	// 정점들 방문했는지 여부 판단해주는 방문 배열
	static int[] vertexDepth;	// 정점들의 깊이를 저장한 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 테스트케이스 10번 순회
		for(int tc=1; tc<=10; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int dataLength = Integer.parseInt(st.nextToken());	// 데이터의 길이
			int startVertex = Integer.parseInt(st.nextToken());	// 시작정점
			graph = new ArrayList<>();	// 그래프 생성
			
			// 정점 만들어주기
			for(int i=0; i<=100; i++) {
				graph.add(new ArrayList<>());
			}
			
			visited = new boolean[101];	// 1 ~ 100번 정점까지 있으므로
			vertexDepth = new int[101];	// 1 ~ 100번 정점까지 있으므로
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<dataLength/2; i++) {
				int fromVertex = Integer.parseInt(st.nextToken());
				int toVertex = Integer.parseInt(st.nextToken());
				
				// 동일한 {from, to} 쌍이 있는 경우
				if(graph.get(fromVertex).contains(toVertex)) {
					continue;	// 넘어감
				}
				
				// 단방향 간선 연결
				graph.get(fromVertex).add(toVertex);
			}
			
			int resultVertex = bfs(startVertex);	
			sb.append("#").append(tc).append(" ").append(resultVertex).append("\n");
		}
		System.out.print(sb);

	}
	
	// 너비우선탐색 메서드
	public static int bfs(int startVertex) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(startVertex);	// 큐에 시작정점 저장
		visited[startVertex] = true;	// 시작정점 방문처리
		int maxDepth = Integer.MIN_VALUE;	// 최대 깊이값 최소로 일단 설정
		
		// 큐가 빌 때 까지 반복
		while(!queue.isEmpty()) {
			int nowVertex = queue.poll();	// 큐에 저장된 정점 뽑기
			
			// 해당 정점의 길이만큼 탐색 (해당 정점에 연결된 정점들 탐색하기 위해)
			for(int i=0; i<graph.get(nowVertex).size(); i++) {
				// 해당 정점에 연결된 정점이 방문 안된 경우
				if(!visited[graph.get(nowVertex).get(i)]) {
					visited[graph.get(nowVertex).get(i)] = true;	// 해당 연결된 정점 방문처리 한 뒤
					queue.add(graph.get(nowVertex).get(i));	// 해당 정점번호 큐에 저장
					vertexDepth[graph.get(nowVertex).get(i)] = vertexDepth[nowVertex] + 1;	// 연결된 정점 깊이 증가시킴
				}
			}
			
			// 깊이 최대값 갱신하기
			maxDepth = Math.max(maxDepth, vertexDepth[nowVertex]);
		}
		
		// 깊이 최대값과 해당 정점의 깊이가 동일한 경우가 여러 케이스가 있을 수 있는데
		// 그중 가장 정점번호가 큰 것을 반환하면 된다 (숫자가 가장 큰 것)
		for(int i=100; i>=1; i--) {
			// 깊이 최대값과 해당 정점의 깊이와 같은 경우
			if(maxDepth == vertexDepth[i]) {
				return i;	// 해당 정점 번호 반환
			}
		}
		
		// 그 이외의 경우는 -1 반환 (이부분은 사실 안걸림)
		return -1;
	}

}
