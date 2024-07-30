import java.util.*;
import java.io.*;

public class Main {
	
	// 좌표 정보 및 점프 횟수를 담고 있는 내부 클래스
	static class Position {
		int x;
		int jump;
		
		public Position(int x, int jump) {
			this.x = x;
			this.jump = jump;
		}
	}

	static int N; // 미로의 크기
	static int[] map; // 미로를 나타내는 1차원 배열
	static boolean[] visited; // 방문 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N]; // [0] ~ [N-1]
		visited = new boolean[N]; // [0] ~ [N-1]
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		int jumpCount = bfs(); // 최소 점프 횟수를 구하기 위한 너비우선탐색 실시
		
		System.out.println(jumpCount);
	}
	
	// 미로에서 가장 왼쪽 끝에서 가장 오른쪽 끝을 가기 위해 최소 점프 횟수를 구하는 메서드 (너비우선탐색)
	public static int bfs() {
		// 너비우선탐색 알고리즘을 사용하기 위해 큐 선언 및 생성
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(0, 0)); // 시작 좌표 및 처음 점프 횟수 큐에 저장
		visited[0] = true; // 시작 좌표 방문 처리
		
		while (!queue.isEmpty()) {
			// 큐에서 현재 좌표 정보 뽑아냄
			Position now = queue.poll();
			int nowX = now.x;
			int nowJump = now.jump;
			
			// 현재 좌표가 도착지점에 도달한 경우 (가장 오른쪽 끝에 도달한 경우)
			if (nowX == N-1) {
				return nowJump; // 현재까지 점프한 횟수 반환
			}
			
			// 1부터 해당 좌표의 쓰여진 칸의 수(map[nowX])까지 탐색
			for (int i=1; i<=map[nowX]; i++) {
				int nextX = nowX + i; // 탐색한 다음 좌표
				
				// 탐색한 다음 좌표가 N이 넘은 경우
				if (nextX >= N) {
					break; // 해당 반복문 종료 
				}
				
				// 탐색한 좌표가 이미 방문한 경우
				if (visited[nextX]) {
					continue; // 다음 좌표 탐색하게끔 넘어감
				}
				
				// 큐에 탐색한 좌표 정보 및 현재 점프한 횟수 + 1 한 뒤 저장
				queue.add(new Position(nextX, nowJump + 1));
				visited[nextX] = true; // 탐색한 좌표 방문 처리
			}
			
		}
		
		// 너비우선탐색 실시했는데도 도착지점에 도달할 수 없는 경우 -1 반환
		return -1;
	}

}