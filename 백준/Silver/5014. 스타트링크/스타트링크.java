import java.util.*;
import java.io.*;

public class Main {

	static int F;	// 총 F층으로 이루어진 사무실
	static int S;	// 강호가 현재 있는 층
	static int G;	// 엘리베이터 타고 이동할 목적지 층
	static int U;	// 위로 가는 버튼 (+U)
	static int D;	// 아래로 가는 버튼 (-D)
	static int[] buttonPush;	// 각 층마다 버튼을 누른 횟수를 저장하는 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 입력 받기
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		// 강호가 있는 현재 층이 목적지 층과 같은 경우
		if(S == G) {
			System.out.println(0);	// 이동할 필요 없으므로 버튼 누를 필요도 없음
			return;	// 뒤에 더 탐색할 필요 없이 메인메서드 종료
		}
		
		buttonPush = new int[F+1];	// [1] ~ [F]층까지 이용할 수 있게끔 배열 방 번호 F+1로
		boolean elevatorOk = bfs(S, G);	// 너비우선탐색 실시
		// 엘리베이터를 이용해서 G층에 갈 수 있는 경우
		if(elevatorOk) {
			System.out.println(buttonPush[G]-1);
		}
		// 갈수 없는 경우
		else {
			System.out.println("use the stairs");
		}
		
	}
	
	// 너비우선탐색 메서드
	public static boolean bfs(int start, int end) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		buttonPush[start] = 1;	// 해당 층의 버튼 누른 횟수 1로 초기화
		
		while(!queue.isEmpty()) {
			int floorNum = queue.poll();	// 현재 엘리베이터 층 번호
			
			// 현재 층이 목적지 층과 같은 경우 (즉, 목적지 층에 도달한 경우)
			if(floorNum == end) {
				return true;	// 엘리베이터로 이동 가능(true) 반환
			}
			
			// 위로 가는 버튼을 눌렀을 때, 층이 유효하고 아직 버튼을 누르지 않았다면 큐에 추가
			if(floorNum-D > 0 && buttonPush[floorNum-D] == 0) {
				queue.add(floorNum-D);
				buttonPush[floorNum-D] = buttonPush[floorNum] + 1;	// 버튼 누른 횟수 증가
			}
			// 아래로 가는 버튼을 눌렀을 때, 층이 유효하고 아직 버튼을 누르지 않았다면 큐에 추가
			if(floorNum+U <= F && buttonPush[floorNum+U] == 0) {
				queue.add(floorNum+U);
				buttonPush[floorNum+U] = buttonPush[floorNum] + 1;	// 버튼 누른 횟수 증가
			}
		}
		
		return false;	// 목적지 층에 도착하지 못한 경우 false 반환
	}

}
