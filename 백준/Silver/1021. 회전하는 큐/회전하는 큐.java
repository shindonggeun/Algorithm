import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 큐의 크기
	static int M; // 뽑아내려고 하는 수의 개수
	static Deque<Integer> deque; // 덱 (양방향 큐) 선언

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		deque = new ArrayDeque<>(); // 덱 생성
		// 1부터 N까지 덱에 숫자를 추가하는 과정
		for (int i=1; i<=N; i++) {
			deque.add(i);
		}
		
		List<Integer> targetList = new ArrayList<>(); // 뽑아내려는 숫자들의 리스트
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<M; i++) {
			int num = Integer.parseInt(st.nextToken());
			targetList.add(num); // 뽑아내려는 숫자를 리스트에 추가
		}
		
		int totalMoveCount = 0; // 총 이동 횟수를 저장할 변수
		
		// 뽑아내려는 숫자들의 리스트 순회
		for (int target: targetList) {
			int idx = 0; // 현재 뽑아내려는 숫자가 덱 내 어디 위치에 있는지 알아내는 변수 (인덱스)  
			
			// 덱에 저장된 숫자 순회
			for (int num: deque) {
				// 덱에 뽑은 숫자가 뽑아내려는 숫자와 같은 경우
				if (num == target) {
					break; // 덱에 저장된 숫자 순회 반복문 종료
				}
				idx++; // 뽑아내려는 숫자 찾을 때까지 인덱스 증가
			}
			
			int leftMoveCount = idx; // 왼쪽으로 이동하는 횟수 (덱의 처음부터 해당 뽑아내려는 숫자까지의 거리)
			int rightMoveCount = deque.size() - idx; // 오른쪽으로 이도하는 횟수 (덱의 끝에서 뽑아내려는 숫자까지의 거리)
			
			// 왼쪽으로 이동하는 횟수가 오른쪽으로 이동하는 횟수보다 작거나 같은 경우 (즉, 왼쪽 이동이 더 효율적인 경우)
			if (leftMoveCount <= rightMoveCount) {
				// 왼쪽으로 이동한 횟수만큼 반복
				for (int i=0; i<leftMoveCount; i++) {
					int leftNum = deque.pollFirst(); // 첫번째 원소를 꺼냄
					deque.addLast(leftNum); // 꺼낸 원소를 마지막으로 보냄 (왼쪽으로 회전)
				}
				totalMoveCount += leftMoveCount; // 왼쪽으로 이동한 횟수를 총 이동한 횟수에 누적
			}
			// 오른쪽으로 이동하는 횟수가 왼쪽으로 이동하는 횟수보다 작은 경우 (즉, 오른쪽으로 이동이 더 효율적인 경우)
			else {
				// 오른쪽으로 이동한 횟수만큼 반복
				for (int i=0; i<rightMoveCount; i++) {
					int rightNum = deque.pollLast(); // 마지막 원소를 꺼냄
					deque.addFirst(rightNum); // 꺼낸 원소를 첫번째로 보냄 (오른쪽으로 회전)
				}
				totalMoveCount += rightMoveCount; // 오른쪽으로 이동한 횟수를 총 이동한 횟수에 누적
			}
			
			deque.pollFirst(); // 위의 과정을 다 거쳤으면 뽑아내려는 수를 찾았으므로 첫 번째 원소 제거
		}
		
		System.out.println(totalMoveCount);

	}

}