import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 수빈이의 현재 위치
	static int K; // 동생의 위치
	static int[] visited; // 방문 여부와 해당 위치까지 걸린 시간을 저장하는 배열
	static int[] count; // 위치별 최단 시간으로 도달하는 방법의 수를 저장하는 배열
	static final int END_POINT = 100_000; // 문제에서 주어진 위치의 최대 값 (0 <= N, K <= 100,000)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 수빈이와 동생의 위치가 같은 경우
		if (N == K) {
			System.out.println(0); // 수빈이가 동생을 찾는 시간 0초
			System.out.println(1); // 수빈이가 동생을 찾는 방법의 수는 1가지
			return; // 메서드 종료
		}
		
		visited = new int[END_POINT+1]; // [0] ~ [100000]
		count = new int[END_POINT+1]; // [0] ~ [100000]
		
		bfs(N, K); // 시작위치에서부터 끝 위치까지 너비우선탐색 실시
		
		// 동생이 있는 위치까지 걸린 시간 출력 (수빈이가 동생을 찾는 가장 빠른 시간)
		System.out.println(visited[K]);
		// 가장 빠른 시간으로 수빈이가 동생을 찾는 방법의 수 출력
		System.out.println(count[K]);
	}
	
	// // 너비우선탐색 알고리즘을 이용하여 수빈이가 동생을 찾는데 걸리는 시간 및 방법의 수를 찾는 메서드
	public static void bfs(int start, int end) {
		// 너비우선탐색 알고리즘을 이용하기 위해 큐 선언 및 생성
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start); // 수빈이의 위치 큐에 저장
		count[start] = 1; // 수빈이의 위치 (시작 위치)에서 시작하는 경로는 1개
		
		while (!queue.isEmpty()) {
			int now = queue.poll(); // 큐에 저장된 수빈이의 현재 위치 뽑아냄
			
			// 3가지 방법 탐색
			for (int i=0; i<3; i++) {
				int next = 0; // 수빈이의 다음 위치 (이동한 위치)
				
				// 첫 번째 경우: 앞으로 한 칸 이동
				if (i == 0) {
					next = now + 1;
				}
				// 두 번째 경우: 뒤로 한 칸 이동
				else if (i == 1) {
					next = now - 1;
				}
				// 세 번째 경우: 순간 이동
				else {
					next = now * 2;
				}
				
				// 수빈이가 이동한 위치가 범위를 벗어난 경우 (즉, 좌표를 벗어난 경우)
				if (next < 0 || next > END_POINT) {
					continue; // 다음 방법으로 탐색하게끔 넘어감
				}
				
				// 수빈이가 이동한 위치가 아직 방문하지 않은 경우 (해당 경우 최단 시간 갱신)
				if (visited[next] == 0) {
					queue.add(next); // 큐에 이동한 위치 저장
					// 이동한 위치에 따른 시간을 현재 위치에서 한 번 이동한 시간 (+1) 기록
					visited[next] = visited[now] + 1;
					count[next] = count[now]; // 현재 위치에서 이동한 경로 수를 그대로 복사
				}
				// 수빈이가 해당 위치 이미 방문했지만 동일한 최단 시간으로 도달한 경우 
				else if (visited[next] == visited[now] + 1) {
					count[next] += count[now]; // 경로 개수를 누적
				}
			}
		}
	}

}