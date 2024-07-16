import java.util.*;
import java.io.*;

public class Main {
	
	static class Belt {
		int durability;
		boolean robot; // 해당 벨트에 로봇이 놓여져있는지 여부
		
		public Belt(int durability, boolean robot) {
			this.durability = durability;
			this.robot = robot;
		}
	}
	
	static int N;
	static int K;
	static LinkedList<Belt> beltList;
	static boolean[] robotVisited;
	static int zeroDurabilityCount;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		beltList = new LinkedList<>();
		
		st = new StringTokenizer(br.readLine());
		
		for (int i=0; i<N*2; i++) {
			int durability = Integer.parseInt(st.nextToken());
			
			beltList.add(new Belt(durability, false));
		}

		robotVisited = new boolean[N*2];
		
		zeroDurabilityCount = 0;
		int stepCount = 0;
		
		while (zeroDurabilityCount < K) {
			stepCount++;
			moveBelt();
			moveRobot();
			placeRobot();
		}
		
		System.out.println(stepCount);
	}
	
	public static void moveBelt() {
		// 벨트 회전시키기
		Belt belt = beltList.removeLast();
		beltList.addFirst(belt);
		
		if (beltList.get(N-1).robot) {
			beltList.get(N-1).robot = false;
		}
	}
	
	public static void moveRobot() {
		// 가장 먼저 올라간 로봇부터 로봇 이동시키기 (올려져있는 로봇들)
		for (int i=N-2; i>=0; i--) {
			if (beltList.get(i).robot && !beltList.get(i+1).robot && beltList.get(i+1).durability > 0) {
				beltList.get(i).robot = false;
				beltList.get(i+1).robot = true;
				beltList.get(i+1).durability--;
				
				if (beltList.get(i+1).durability == 0) {
					zeroDurabilityCount++;
				}
			}
		}
		
		// 내리는 위치에 도달한 로봇인 경우 (문제에서 N번째 벨트에 도달한 로봇인 경우) -> 내려줌
		// 연결리스트는 0번 인덱스부터 시작하므로 N번째 벨트 -> N-1 인덱스이다
		if (beltList.get(N-1).robot) {
			beltList.get(N-1).robot = false; // 해당 벨트에 있는 로봇을 내렸다고 표시
		}
	}
	
	public static void placeRobot() {
		if (beltList.get(0).durability > 0) {
			beltList.get(0).robot = true;
			beltList.get(0).durability--;
			
			if (beltList.get(0).durability == 0) {
				zeroDurabilityCount++;
			}
		}
	}

}
