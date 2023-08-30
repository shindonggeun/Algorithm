import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int[][] map;
	static boolean[] visited;	// 해당 도시들 방문했는지 여부를 판단해주는 방문 배열
	static long minCost;	// 외판원 순회에 필요한 최소 비용
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		minCost = Long.MAX_VALUE;	// 외판원 순회에 필요한 최소 비용 최대로 일단 초기화
		
		// 완전탐색 이용
		for(int i=1; i<=N; i++) {
			visited = new boolean[N+1];	// [1] ~ [N] 까지 해당 도시번호 방문했는지 여부 ([0] => 사용X)
			visited[i] = true;	// 해당 정점(도시번호) 방문처리
			dfs(i, i, 0, 1);	// 깊이우선탐색 실시
		}
		
		System.out.println(minCost);
		
	}
	
	// 깊이우선탐색 메서드 (백트래킹 추가)
	// parameter => 시작 정점(시작 도시), 현재 정점(현재 도시), 순회돌면서 들은 비용, 깊이(도시 순회한 수)
	public static void dfs(int startVertex, int nowVertex, long cost, int depth) {
		// 깊이(방문횟수, 즉 도시순회한 수)가 N인 경우 (즉, 모든 도시 다 방문한 경우) => 종료조건
		if(depth == N) {
			// 현재 도시에서 시작도시로 간 경우 비용이 0이 아닌 경우 (즉, 현재 도시에서 시작도시로 갈 수 있는 경우)
			if(map[nowVertex][startVertex] != 0) {
				cost += map[nowVertex][startVertex];	// 해당 도시로 가는데 드는 비용 더해줌
				minCost = Math.min(minCost, cost);	// 순회에 필요한 최소비용 갱신
			}
			return;	// 메서드 종료
		}
		
		// 1번 도시부터 N번 도시까지 탐색
		for(int i=1; i<=N; i++) {
			// 해당 도시가 방문하지 않았으면서 동시에 현재도시에서 해당 도시까지 갈 수 있는 경우
			if(!visited[i] && map[nowVertex][i] != 0) {
				visited[i] = true;	// 해당 도시 방문처리
				dfs(startVertex, i, cost + map[nowVertex][i], depth+1);	// 시작도시, 현재 해당 도시, 비용, 깊이 + 1
				visited[i] = false;	// 해당 도시 미방문 처리 (백트래킹)
			}
		}
	}

}
