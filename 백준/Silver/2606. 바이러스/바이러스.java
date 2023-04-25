import java.util.*;
import java.io.*;

public class Main {

	static int N;	// 컴퓨터의 수 (정점의 개수)
	static int M;	// 네트워크 상에 직접 연결되어 있는 컴퓨터 쌍의 개수 (간선의 개수)
	static ArrayList<Integer>[] arr;
	static boolean[] visited;
	//static StringBuilder sb;
	static int count;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(br.readLine());
		arr = new ArrayList[N+1];
		visited = new boolean[N+1];
		//sb = new StringBuilder();
		count = 0;
		
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
		
		dfs(1);	// 1번 정점에서부터 시작
		System.out.println(count-1);	// 1번 정점을 뺀 바이러스에 걸린 컴퓨터의 개수 출력

	}
	
	// 깊이우선탐색 알고리즘 이용
	public static void dfs(int index) {
		visited[index] = true;
		//sb.append(index+ " ");	// 현재 방문한 값 저장
		count++;		// 방분한 정점 개수 증가
		
		// 현재 방문한 노드에 해당하는 리스트에 담겨있는 값들 하나씩 뽑아내기
		for(int i: arr[index]) {
			// 방문안한 노드인 경우
			if(!visited[i]) {
				dfs(i);	// 그 노드에 해당하는 리스트들 탐색할 수 있도록 재귀 호출
			}
		}
	}

}
