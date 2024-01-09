import java.util.*;
import java.io.*;

public class Main {
	
	static int N;	// 집하장의 개수
	static int M;	// 집하장 간 경로의 개수
	static int[][] dist;	// 각 집하장 간 최단 거리를 저장할 배열
	// 각 집하장에서 다음 집하장까지 최단 경로를 통해 이동할 때 제일 먼저 이동해야활 집하장을 저장할 배열 (경로 저장)
	static int[][] path;	
	static final int INF = 987654321;	// 무한대를 나타내는 값

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dist = new int[N+1][N+1];	// [1][1] ~ [N][N]
		path = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			Arrays.fill(dist[i], INF);	// 각 집하장의 모든 경로 무한대로 초기화
			dist[i][i] = 0;	// 자기 자신으로의 거리는 0으로 초기화
			for(int j=1; j<=N; j++) {
				if(i != j) {
					path[i][j] = j;	// 다른 집하장으로 가는 최초 경로를 해당 집하장으로 초기화
				}
			}
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int fromVertex = Integer.parseInt(st.nextToken());
			int toVertex = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			dist[fromVertex][toVertex] = weight;
			dist[toVertex][fromVertex] = weight;
		}
		
		floydWarshall();	// 플로이드-워셜 알고리즘을 이용한 메서드 호출
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(i == j) {
					sb.append("-").append(" ");	// 자기 자신으로의 경로는 '-'로 표시
				}
				else {
					sb.append(path[i][j]).append(" ");	// 최단 경로의 첫 번째 집하장 출력
				}
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
	
	// 각 집하장들의 최단거리를 구해주는 메서드 (플로이드-워셜 알고리즘 이용)
	public static void floydWarshall() {
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					// i에서 j로 가는 최단 거리가 i에서 k를 거쳐 k에서 j로 가는 거리보다 긴 경우
					if(dist[i][k] + dist[k][j] < dist[i][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];	// 최단 경로 (거리)를 업데이트
						path[i][j] = path[i][k];	// 최단 경로의 첫 번째 집하장을 업데이트
					}
				}
			}
		}
	}

}
