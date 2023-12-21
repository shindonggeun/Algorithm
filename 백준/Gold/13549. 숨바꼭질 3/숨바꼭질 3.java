import java.util.*;
import java.io.*;

public class Main {
	
	// 위치와 시간을 저장할 내부 클래스
	static class Edge {
		int toVertex;	// 다음 위치
		int weight;	// 걸리는 시간 (가중치)
		
		public Edge(int toVertex, int weight) {
			this.toVertex = toVertex;
			this.weight = weight;
		}
	}
	
	static int N;	// 수빈이의 위치
	static int K;	// 동생의 위치
	static int[] dist;	// 각 위치까지의 최소 시간을 저장하는 배열
	static boolean[] visited;	// 각 위치의 방문 여부를 체크하는 배열
	static final int INF = Integer.MAX_VALUE;	// 무한대를 나타내는 상수
	static final int MAX = 100000;	// 위치의 최대 범위

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dist = new int[MAX+1];	// [0] ~ [100000]
		visited = new boolean[MAX+1];
		Arrays.fill(dist, INF);	
		
		bfsZeroOne(N);	// 0-1 BFS 메서드 실행
		
		System.out.println(dist[K]);	// 수빈이가 동생이 있는 위치 K까지 가는데 걸리는 최소 시간 출력 
	}
	
	// 수빈이가 동생을 찾는 가장 빠른시간을 구하는 메서드 (0-1 BFS 알고리즘 이용)
	public static void bfsZeroOne(int start) {
		// 0-1 BFS 알고리즘을 이용하기 위해 덱을 선언 및 생성
		Deque<Edge> deque = new ArrayDeque<>();
		deque.add(new Edge(start, 0));	// 덱에 수빈이의 시작 위치와 동생을 찾는데 걸리는 시간 추가
		dist[start] = 0;	// 시작 위치의 최소 시간을 0으로 설정
		
		// 0-1 BFS 알고리즘 이용
		while(!deque.isEmpty()) {
			// 현재 위치와 시간 정보를 덱에서 뽑아냄
			Edge now = deque.poll();
			int nowPosition = now.toVertex;
			int nowTime = now.weight;
			
			// 현재 위치가 방문되지 않은 경우
			if(!visited[nowPosition]) {
				visited[nowPosition] = true;	// 현재 위치 방문처리
				
				// 순간이동인 경우 (수빈이의 위치가 X인 경우 0초 후에 2 * X의 위치로 이동)
				if(nowPosition * 2 <= MAX && dist[nowPosition * 2] > nowTime) {
					// 현재위치의 * 2 한 위치의 최소 시간은 현재까지 걸린 시간
					dist[nowPosition * 2] = nowTime;
					// 가중치 0이므로 덱의 앞에 추가 (0-1 BFS 알고리즘의 핵심)
					deque.addFirst(new Edge(nowPosition * 2, nowTime));
				}
				
				// 걷기(뒤로)인 경우 (수빈이의 위치가 X인 경우 1초 후에 X-1의 위치로 이동)
				if(nowPosition - 1 >= 0 && dist[nowPosition - 1] > nowTime + 1) {
					// 현재위치의 - 1 한 위치의 최소 시간은 현재까지 걸린 시간 + 1
					dist[nowPosition - 1] = nowTime + 1;
					// 가중치 1이므로 덱의 뒤에 추가 (0-1 BFS 알고리즘의 핵심)
					deque.addLast(new Edge(nowPosition - 1, nowTime + 1));
				}
				
				// 걷기(앞으로)인 경우 (수빈이의 위치가 X인 경우 1초 후에 X+1의 위치로 이동)
				if(nowPosition + 1 <= MAX && dist[nowPosition + 1] > nowTime + 1) {
					// 현재위치의 + 1 한 위치의 최소 시간은 현재까지 걸린 시간 + 1
					dist[nowPosition + 1] = nowTime + 1;
					// 가중치 1이므로 덱의 뒤에 추가 (0-1 BFS 알고리즘의 핵심)
					deque.addLast(new Edge(nowPosition + 1, nowTime + 1));
				}
			}
			
		}
	}

}
