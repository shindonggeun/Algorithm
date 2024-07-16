import java.util.*;
import java.io.*;

public class Main {
	
	// 벨트의 정보를 담고 있는 내부 클래스
	static class Belt {
		int durability; // 해당 벨트의 내구도
		boolean robot; // 해당 벨트에 로봇이 놓여져있는지 여부
		
		public Belt(int durability, boolean robot) {
			this.durability = durability;
			this.robot = robot;
		}
	}
	
	static int N; // 벨트의 길이
	static int K; // 내구도가 0인 칸의 개수가 K개 이상인 경우 종료
	static LinkedList<Belt> beltList; // 벨트들의 정보를 담고있는 컨베이어 벨트 리스트 (연결리스트)
	static int zeroDurabilityCount; // 내구도가 0인 벨트(칸)의 개수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		beltList = new LinkedList<>(); // 컨베이어 벨트 리스트 초기화 (연결 리스트)
		
		st = new StringTokenizer(br.readLine());
		
		for (int i=0; i<N*2; i++) {
			int durability = Integer.parseInt(st.nextToken());
			
			beltList.add(new Belt(durability, false)); // 해당 벨트 내구도 및 로봇 있는지 여부 연결 리스트에 저장
		}
		
		zeroDurabilityCount = 0; // 내구도가 0인 칸의 개수 0으로 초기화
		int stepCount = 0; // 단계 수 0으로 초기화
		
		// 종료 조건 만족할 때까지 반복
		while (zeroDurabilityCount < K) {
			stepCount++; // 단계 수 증가
			moveBelt(); // 벨트 회전 시키기
			moveRobot(); // 로봇 이동 시키기
			placeRobot(); // 로봇 올리기
		}
		
		// 단계 수 출력
		System.out.println(stepCount);
	}
	
	// 컨베이어 벨트를 회전시키는 메서드
	public static void moveBelt() {
		// 벨트 회전시키기 위해 마지막 요소를 제거한 뒤 첫 번째 위치에 해당 요소(벨트) 추가
		Belt belt = beltList.removeLast();
		beltList.addFirst(belt);
		
		// 컨베이어 벨트에서 내리는 위치에 (N번째 -> 연결 리스트는 0번째 인덱스부터 시작하므로 N-1 인덱스) 로봇이 있는 경우
		if (beltList.get(N-1).robot) {
			beltList.get(N-1).robot = false; // 해당 위치에 로봇 내렸다는 표시
		}
	}
	
	// 컨베이어 벨트에 놓여져 있는 로봇들을 이동 시키는 메서드
	public static void moveRobot() {
		// 가장 먼저 올라간 로봇부터 로봇 이동시키기 위해 역순으로 반복 (올려져있는 로봇들)
		// 즉, N번째 벨트 (인덱스로는 N-1)에 놓여지면 로봇을 내리기 때문에 N-1번째 (N-2 인덱스)부터 탐색을 시작한다
		for (int i=N-2; i>=0; i--) {
			// 컨베이어 벨트에 해당 칸에 로봇이 있으면서 동시에 다음 칸에 로봇이 없으면서 동시에 다음 칸에 내구도가 0보다 큰 경우
			if (beltList.get(i).robot && !beltList.get(i+1).robot && beltList.get(i+1).durability > 0) {
				beltList.get(i).robot = false; // 해당 칸에 로봇 없음 (즉, 다음 칸으로 이동시키기 위해)
				beltList.get(i+1).robot = true; // 다음 칸에 로봇 있음 (다음 칸으로 로봇 이동함)
				beltList.get(i+1).durability--; // 다음 칸의 내구도 감소
				
				// 다음 칸의 내구도가 0이 된 경우
				if (beltList.get(i+1).durability == 0) {
					zeroDurabilityCount++; // 내구도가 0인 칸의 개수 증가
				}
			}
		}
		
		// 위 과정 거쳤으면 마지막 N번째 벨트에 있는 로봇도 탐색해줘야함 (즉, 인덱스 N-1)
		// 내리는 위치에 도달한 로봇인 경우 (문제에서 N번째 벨트에 도달한 로봇인 경우) -> 내려줌
		// 연결리스트는 0번 인덱스부터 시작하므로 N번째 벨트 -> N-1 인덱스이다
		if (beltList.get(N-1).robot) {
			beltList.get(N-1).robot = false; // 해당 벨트에 있는 로봇을 내렸다고 표시
		}
	}
	
	// 컨베이어 벨트에 새로운 로봇을 놓는 메서드
	public static void placeRobot() {
		// 올리는 위치 (0번 인덱스)에 내구도가 0보다 큰 경우
		if (beltList.get(0).durability > 0) {
			beltList.get(0).robot = true; // 해당 위치에 로봇 놓여짐
			beltList.get(0).durability--; // 해당 위치에 내구도 감소
			
			// 해당 위치에 내구도가 0이 된 경우
			if (beltList.get(0).durability == 0) {
				zeroDurabilityCount++; // 내구도가 0인 개수 증가
			}
		}
	}

}