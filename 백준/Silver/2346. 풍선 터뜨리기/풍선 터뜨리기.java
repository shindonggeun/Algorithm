import java.util.*;
import java.io.*;

public class Main {
	
	// 풍선의 정보를 저장하고 있는 내부 클래스
	static class Ballon {
		int moveNum; // 이동할 횟수가 적혀있는 번호 (양수 -> 오른쪽, 음수 -> 왼쪽)
		int idx; // 풍선의 번호 (인덱스)
		
		public Ballon(int moveNum, int idx) {
			this.moveNum = moveNum;
			this.idx = idx;
		}
	}
	
	static int N; // 풍선의 개수
	static Deque<Ballon> deque; // 양방향 큐 덱 선언

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		deque = new ArrayDeque<>(); // 덱 생성
		
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			int num = Integer.parseInt(st.nextToken());
			deque.add(new Ballon(num, i)); // 덱에 해당 풍선의 이동할 횟수가 적혀있는 번호와 풍선의 번호 저장
		}
		
		StringBuilder sb = new StringBuilder();
		
		Ballon now = deque.pollFirst(); // 덱에서 현재 맨앞에 있는 풍선 정보 뽑아냄
		int moveCount = now.moveNum; // 현재 뽑아낸 풍선의 이동할 횟수가 적힌 번호 저장
		sb.append(now.idx).append(" "); // 현재 풍선의 번호 출력하게끔 (1)
		
		while (!deque.isEmpty()) {
			// 1. 풍선 이동하는 과정
			// 풍선의 이동할 횟수가 적힌 번호가 양수인 경우
			if (moveCount > 0) {
				// 오른쪽으로 이동하는 과정
				// 아까 본인 풍선은 터트렸으므로 본인 풍선을 제외하고 이동하므로 풍선의 이동할 횟수-1까지 반복
				for (int i=0; i<moveCount-1; i++) {
					// 덱에서 맨 처음값을 뽑아내서 덱의 맨 마지막에 저장 (오른쪽으로 이동)
					deque.addLast(deque.pollFirst());
				}
			}
			// 풍선의 이동할 횟수가 적힌 번호가 음수인 경우
			else {
				// 왼쪽으로 이동하는 과정
				for (int i=0; i<Math.abs(moveCount); i++) {
					// 덱에서 맨 마지막값을 뽑아내서 덱의 맨 처음에 저장 (왼쪽으로 이동)
					deque.addFirst(deque.pollLast());
				}
			}
			
			// 2. 풍선 터트리는 과정
			Ballon next = deque.poll(); // 덱에서 현재 맨 앞에 있는 값 뽑아냄 (다음 풍선)ㄴ
			moveCount = next.moveNum; // 다음 풍선의 이동할 횟수가 적힌 번호 저장
			sb.append(next.idx).append(" "); // 다음 터진 풍선의 번호 출력하게끔 
		}
		
		System.out.println(sb);
		
	}

}