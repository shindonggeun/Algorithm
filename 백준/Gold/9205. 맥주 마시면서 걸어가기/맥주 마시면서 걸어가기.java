import java.util.*;
import java.io.*;

public class Main {
	
	// 좌표 정보를 저장해주는 내부 클래스
	static class Position {
		int x;
		int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;	// 맥주를 파는 편의점의 개수
	static Position[] storeArr;	// 편의점의 좌표정보를 저장한 배열
	static boolean[] visited;	// 각 편의점 방문 여부를 판단해주는 방문 배열
	static boolean isPossible;	// 페스티벌에 갈 수 있는지 여부를 판단해주는 변수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=testCase; tc++) {
			N = Integer.parseInt(br.readLine());
			// 상근이네 집 좌표 입력받는 과정
			st = new StringTokenizer(br.readLine());
			int homeX = Integer.parseInt(st.nextToken());
			int homeY = Integer.parseInt(st.nextToken());
			
			storeArr = new Position[N];	// [0] ~ [N]
			visited = new boolean[N];
			isPossible = false;
			
			// 편의점 좌표 입력받는 과정
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int storeX = Integer.parseInt(st.nextToken());
				int storeY = Integer.parseInt(st.nextToken());
				
				storeArr[i] = new Position(storeX, storeY);
			}
			
			// 락 페스티벌 좌표 입력받는 과정
			st = new StringTokenizer(br.readLine());
			int festivalX = Integer.parseInt(st.nextToken());
			int festivalY = Integer.parseInt(st.nextToken());
			
			// 상근이네 집(시작지점)에서부터 락 페스티벌(도착지점)까지 도착할 수 있는지 여부 체크하는 메서드 호출
			arriveCheck(homeX, homeY, festivalX, festivalY);
			
			if(isPossible) {
				sb.append("happy").append("\n");
			} 
			else {
				sb.append("sad").append("\n");
			}
		}
		System.out.print(sb);
	}
	
	// 시작지점에서 도착지(페스티벌 장소)까지 도착할 수 있는지 여부를 체크하는 메서드 (너비우선탐색 알고리즘 이용)
	public static void arriveCheck(int startX, int startY, int endX, int endY) {
		// 너비우선탐색 알고리즘을 이용하기 위해 큐 선언 및 생성
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY));	// 시작지점 좌표 정보 큐에 저장
		
		// 너비우선탐색 알고리즘 이용
		while(!queue.isEmpty()) {
			// 큐에서 현재 좌표 정보 뽑아냄
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 현재 좌표에서부터 도착지(페스티벌 장소)까지 갈 수 있는 경우
			if(calculate(nowX, nowY, endX, endY)) {
				isPossible = true;	// 갈 수 있음
				break;	// 메서드 종료
			}
			
			for(int i=0; i<N; i++) {
				// 해당 편의점이 방문되지 않은 경우
				if(!visited[i]) {
					// 편의점의 좌표 정보 추출
					int storeX = storeArr[i].x;
					int storeY = storeArr[i].y;
					
					// 현재 좌표에서부터 해당 편의점의 좌표까지 거리 계산하기
					// 해당 지점까지 갈 수 있는 경우
					if(calculate(nowX, nowY, storeX, storeY)) {
						visited[i] = true;	// 해당 편의점 방문 처리 
						queue.add(new Position(storeX, storeY));	// 해당 편의점의 좌표 정보 큐에 저장
					}
				}
			}
		}
	}
	
	// 해당 현재 지점부터 도착 지점까지 거리를 계산하여 도착지점까지 갈 수 있는지 여부를 판단해주는 메서드
	public static boolean calculate(int nowX, int nowY, int endX, int endY) {
		// 현재 지점부터 도착 지점까지 맨해튼 거리를 이용해서 계산
		int distance = Math.abs(nowX - endX) + Math.abs(nowY - endY);
		// 해당 거리가 1000 이하인 경우
		if(distance <= 1000) {
			return true;	// 도착 지점까지 갈 수 있음
		}
		// 그 이외의 경우는 도착지점까지 갈 수 없음
		return false;
	}

}
