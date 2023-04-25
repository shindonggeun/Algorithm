import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 사람 수(정점의 수)
	static int M;	// 부모 자식들간의 관계의 수 (간선의 수)
	static ArrayList<Integer>[] arr;
	static boolean[] visited;
	static int count;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		arr = new ArrayList[N+1];
		visited = new boolean[N+1];
		count = -1;
		
		for(int i=0; i<arr.length; i++) {
			arr[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			arr[a].add(b);
			arr[b].add(a);
		}
		
		for(int i=0; i<arr.length; i++) {
			Collections.sort(arr[i]);	// 정점에 연결된 간선들 오름차순 정렬
		}
		
		dfs(start, end, 0);	// 해당 정점부터 시작, 종료정점
		System.out.println(count);
	}
	
	public static void dfs(int index, int end, int cnt) {
		if(index == end) {
			count = cnt;
			return;
		}
		visited[index] = true;
		
		// 현재 방문한 노드에 해당하는 리스트에 담겨있는 값들 하나씩 뽑아내기
		for(int i: arr[index]) {
			// 방문안한 노드인 경우
			if(!visited[i]) {
				dfs(i, end, cnt+1);	// 그 노드에 해당하는 리스트들 탐색할 수 있도록 재귀 호출
			}
		}	
		
	}

}
