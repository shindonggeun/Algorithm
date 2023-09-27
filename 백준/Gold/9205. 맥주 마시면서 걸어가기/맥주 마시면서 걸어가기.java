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
	
	static List<Position> storeList;	// 편의점들의 좌표정보를 저장한 리스트
	static boolean[] visited;	// 해당 편의점 방문 여부 체크해주는 배열
	static boolean isPossible;	// 락 페스티벌(도착지)까지 갈 수 있는지 판단해주는 변수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());	// 편의점의 개수 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			storeList = new ArrayList<>();	// 편의점의 좌표정보를 담은 리스트
			visited = new boolean[N];
			isPossible = false;
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				storeList.add(new Position(x, y));
			}
			
			st = new StringTokenizer(br.readLine());
			int endX = Integer.parseInt(st.nextToken());
			int endY = Integer.parseInt(st.nextToken());
			
			bfs(startX, startY, endX, endY);
			
			String happyOrSad = isPossible ? "happy" : "sad";
			sb.append(happyOrSad).append("\n");
		}
		System.out.print(sb);
		
	}
	
	public static void bfs(int startX, int startY, int endX, int endY) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(new Position(startX, startY));
		
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			int nowX = now.x;
			int nowY = now.y;
			
			// 해당 좌표에서 페스티벌 좌표(도착지)까지 갈수 있는 경우 
			if(calculateDistance(nowX, nowY, endX, endY)) {
				isPossible = true;	// 갈수있음
				return;	// 메서드 종료
			}
			
			// 편의점들 탐색하기
			for(int i=0; i<storeList.size(); i++) {
				// 해당 편의점 방문 안한 경우
				if(!visited[i]) {
					int storeX = storeList.get(i).x;
					int storeY = storeList.get(i).y;
					
					// 해당 편의점까지 갈 수 있는 거리인 경우
					if(calculateDistance(nowX, nowY, storeX, storeY)) {
						visited[i] = true;	// 해당 편의점 방문 처리
						queue.add(new Position(storeX, storeY));	// 편의점까지 갔으므로 편의점의 좌표 큐에 저장
					}
					// 해당 편의점까지 갈 수 없는 거리인 경우
					else {
						continue;	// 넘어감 (다음 편의점 좌표 탐색)
					}
				}	
			}
		}
		
	}
	
	// 해당 지점에서 도착지점까지의 거리 계산해주는 메서드
	public static boolean calculateDistance(int nowX, int nowY, int endX, int endY) {
		int distance = Math.abs(nowX - endX) + Math.abs(nowY - endY);
		// 거리가 1000 이하인 경우
		if(distance <= 1000) {
			return true;	// 도착지점까지 갈 수 있음
		}
		// 그 이외의 경우는 갈 수 없음
		return false;
	}

}
