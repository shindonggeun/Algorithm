import java.util.*;
import java.io.*;

public class Main {

	static int N;	// 정점의 개수
	static int M;	// 간선의 개수
	static int V;	// 탐색을 시작할 정점의 번호
	static ArrayList<Integer>[] arr;	// 간선간 연결상태를 나타내는 ArrayList 배열
	static boolean[] visited;	// 정점들 방문 여부 배열
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		arr = new ArrayList[N+1];	// ArrayList 배열 초기화
		visited = new boolean[N+1];
		sb = new StringBuilder();
		
		for(int i=0; i<arr.length; i++) {
			arr[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());	// 시작정점
			int b = Integer.parseInt(st.nextToken());	// 끝정점
			
			// 정점간 간선 연결해주기
			arr[a].add(b);
			arr[b].add(a);
		}
		
		for(int i=0; i<arr.length; i++) {
			Collections.sort(arr[i]);	// 정점에 연결된 간선들 오름차순 정렬
		}
		
		dfs(V);	// 시작정점부터 깊이우선탐색 시작
		sb.append("\n");
		visited = new boolean[N+1];	// 다시 방문했던 배열 초기화해줌
		bfs(V);	// 시작정점부터 너비우선탐색 시작
		System.out.println(sb);

	}
	// 깊이우선탐색 알고리즘 이용
	public static void dfs(int index) {
		visited[index] = true;
		sb.append(index+ " ");	// 현재 방문한 값 저장
		
		// 현재 방문한 노드에 해당하는 리스트에 담겨있는 값들 하나씩 뽑아내기
		for(int i: arr[index]) {
			// 방문안한 노드인 경우
			if(!visited[i]) {
				dfs(i);	// 그 노드에 해당하는 리스트들 탐색할 수 있도록 재귀 호출
			}
		}
	}
	// 너비우선탐색 알고리즘 이용
	public static void bfs(int index) {
		visited[index] = true;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(index);	// 시작 정점 큐에 저장
		
		// 큐가 비어질때까지 반복함
		while(!queue.isEmpty()) {
			int node = queue.poll();	// 큐에 저장된 정점 꺼냄
			sb.append(node + " ");		// 현재 방문한 값 저장
			// 현재 방문한 노드에 해당하는 리스트에 담겨있는 값들 하나씩 뽑아내기
			for(int i: arr[node]) {
				// 방문하지 않은 정점인 경우
				if(!visited[i]) {
					queue.add(i);	// 큐에 해당 정점 저장
					visited[i] = true;	// 해당 정점 방문처리
				}
			}
		}
	}

}