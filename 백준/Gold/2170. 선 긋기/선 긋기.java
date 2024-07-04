import java.util.*;
import java.io.*;

public class Main {
	
	// 선의 좌표 정보를 담고 있는 내부 클래스
	static class Line {
		int x; // 시작 위치
		int y; // 끝 위치
		
		public Line(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N; // 선을 그은 횟수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		// 우선순위 큐 선언 및 생성
		PriorityQueue<Line> pq = new PriorityQueue<>((a, b) -> {
			// 선의 시작점이 같은 경우
			if (a.x == b.x) {
				// 2순위인 선의 도착점을 기준으로 오름차순 정렬
				return a.y - b.y;
			}
			// 1순위인 선의 시작점을 기준으로 오름차순 정렬
			return a.x - b.x;
		});
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()); // 시작점 입력
			int y = Integer.parseInt(st.nextToken()); // 도착점 입력
			
			// 우선순위 큐에 해당 선의 정보 저장
			pq.add(new Line(x, y));
		}
		
		Line first = pq.poll(); // 우선순위 큐에서 처음 뽑은 선의 정보 저장
		
		int start = first.x; // 처음 뽑은 선의 시작점
		int end = first.y; // 처음 뽑은 선의 도착점
		int total = 0; // 선의 총 길이
		
		// 우선순위 큐 빌때까지 반복
		while (!pq.isEmpty()) {
			Line now = pq.poll(); // 우선순위 큐에서 현재 뽑은 선의 정보
			
			// 현재 뽑은 선의 시작점이 전에 뽑은 선의 도착점보다 큰 경우
			// 해당 경우는 선이 이어지지 않고 끊긴 경우임
			if (now.x > end) {
				// 전에 뽑은 선의 길이를 구해서 선의 총 길이에 누적시킴
				total += end - start;
				start = now.x; // 아까 뽑은 선의 시작점을 현재 뽑은 선의 시작점으로 갱신
				end = now.y; // 아까 뽑은 선의 도착점을 현재 뽑은 선의 도착점으로 갱신
				continue; // 다음 선의 정보 확인하게끔 넘어감
			}
			
			// 현재 뽑은 선의 도착점이 전에 뽑은 선의 도착점보다 큰 경우
			if (now.y > end) {
				end = now.y; // 아까 뽑은 선의 도착점을 현재 뽑은 선의 도착점으로 갱신
			}
		}
		
		// 마지막 선의 정보까지 선의 총 길이에 계산해주기 위해 마지막 선의 길이 구해서 선의 총 길이에 누적
		total += end - start;
		
		System.out.println(total);
	}

}