import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 수빈이의 현재 위치
	static int K; // 동생의 위치
	static int[] visited; // 방문 여부와 해당 위치까지 걸린 시간을 저장하는 배열
	static final int END_POINT = 100_000; // 문제에서 주어진 위치의 최대 값 (0 <= N, K <= 100,000)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 수빈이와 동생의 위치가 같은 경우
		if (N == K) {
			System.out.println(0);
			return; // 메서드 종료
		}
		
		visited = new int[END_POINT+1]; // [0] ~ [100000]
		
		bfs(N, K); // 시작위치에서부터 끝 위치까지 너비우선탐색 실시
		
		System.out.println(visited[K]); // 동생이 있는 위치까지 걸린 시간 출력
	}
	
	// 너비우선탐색 알고리즘을 이용하여 수빈이의 현재 위치에서부터 동생이 있는 위치까지 가는데 몇초가 걸리는지 확인하는 메서드
	public static void bfs(int start, int end) {
		// 너비우선탐색 알고리즘을 이용하기 위해 큐 선언 및 생성
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start); // 수빈이의 현재 위치 (시작 위치) 큐에 저장
		
		while (!queue.isEmpty()) {
			int now = queue.poll(); // 큐에 저장된 수빈이의 현재 위치 뽑아냄
			
			// 수빈이의 현재 위치가 동생이 있는 위치와 같은 경우 (즉, 동생이 있는곳에 도달한 경우)
			if (now == end) {
				break; // 너비우선탐색 종료하게끔 해당 반복문 빠져나옴
			}
			
			// 3가지 방법 탐색
			for (int i=0; i<3; i++) {
				int next = 0; // 수빈이의 다음 위치 (이동한 위치)
				
				// i가 0인 경우 -> 수빈이가 x 위치인 경우 1초 후에 x + 1
				if (i == 0) {
					next = now + 1;
				}
				// i가 1인 경우 -> 수빈이가 x 위치인 경우 1초 후에 x - 1
				else if (i == 1) {
					next = now - 1;
				}
				// i가 2인 경우 -> 수빈이가 x 위치인 경우 1초 후에 x * 2
				else {
					next = now * 2;
				}
				
				// 수빈이가 이동한 위치가 0 미만 또는 끝점 (100,000)을 초과한 경우 (즉, 좌표를 벗어난 경우)
				if (next < 0 || next > END_POINT) {
					continue; // 다음 방법으로 탐색하게끔 넘어감
				}
				
				// 수빈이가 이동한 위치가 아직 방문하지 않은 위치인 경우
				if (visited[next] == 0) {
					queue.add(next); // 큐에 이동한 위치 저장
					// 이동한 위치에 따른 시간을 현재 위치에서 한 번 이동한 시간 (+1) 기록
					visited[next] = visited[now] + 1;
				}
			}
		}
	}

}