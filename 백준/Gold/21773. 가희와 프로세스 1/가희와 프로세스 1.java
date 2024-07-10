import java.util.*;
import java.io.*;

public class Main {
	
	// 프로세스의 정보를 나타내는 내부 클래스
	static class Process {
		int id; // 프로세스의 아이디
		int currentTime; // 프로세스의 현재 남아있는 시간
		int priority; // 우선순위
		
		public Process(int id, int currentTime, int priority) {
			this.id = id;
			this.currentTime = currentTime;
			this.priority = priority;
		}
	}
	
	static int T; // 총 실행 시간
	static int N; // 프로세스의 수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		// 우선순위 큐 선언 및 생성
		PriorityQueue<Process> pq = new PriorityQueue<>((a, b) -> {
			// 우선순위가 같은 경우
			if (a.priority == b.priority) {
				// 2순위인 아이디 기준으로 오름차순 정렬 (아이디가 낮은 순으로 정렬)
				return a.id - b.id;
			}
			// 1순위인 우선순위 기준으로 내림차순 정렬 (우선순위가 높은 순으로 정렬)
			return b.priority - a.priority;
		});
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int id = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			int priority = Integer.parseInt(st.nextToken());
			
			pq.add(new Process(id, time, priority)); // 우선순위 큐에 해당 프로세스 저장
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<T; i++) {
			// 현재 우선순위 큐에서 가장 높은 우선순위의 프로세스 뽑아냄
			Process now = pq.poll();
			// 시간복잡도를 위해 문제에서 주어진 조건 역으로 해당 프로세스의 우선순위를 감소시킴
			now.priority--; // 해당 프로세스의 우선순위 감소
			now.currentTime--; // 해당 프로세스의 현재 남아있는 시간 감소
			
			sb.append(now.id).append("\n"); // 해당 프로세스의 아이디 출력
			
			// 해당 프로세스의 현재 남있는 시간이 0인 경우
			if (now.currentTime == 0) {
				continue; // 다음 우선순위 큐에 남아있는 프로세스 탐색
			}
			
			// 우선순위 큐에 해당 프로세스 저장
			pq.add(now);
		}
		
		System.out.print(sb);
	}

}