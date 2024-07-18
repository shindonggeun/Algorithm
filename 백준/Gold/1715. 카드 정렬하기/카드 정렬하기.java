import java.util.*;
import java.io.*;

public class Main {

	static int N; // 숫자 카드 묶음의 개수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		// 카드 묶음의 크기를 저장할 우선순위 큐 선언 및 생성
		// 카드 묶음의 크기 오름차순 정렬
		PriorityQueue<Integer> pq = new PriorityQueue<>(); 
		
		for (int i=0; i<N; i++) {
			int cardSize = Integer.parseInt(br.readLine()); // 카드 묶음의 크기 입력
			pq.add(cardSize); // 카드 묶음의 크기 우선순위 큐에 저장
		}
		
		int minCount = 0; // 최소 비교 횟수
		int tempCount = 0; // 두 카드 묶음을 합친 임시 비교 횟수
		int start = 0; // 두 카드 묶음을 합친 횟수를 세기 위한 변수
		
		// 우선순위 큐가 비어있지 않을 때까지 반복
		while (!pq.isEmpty()) {
			tempCount += pq.poll(); // 가장 작은 카드 묶음을 꺼내 임시 비교 횟수에 누적
			start++; // 두 카드 묶음을 합친 횟수를 증가시킴
			
			// 두 카드 묶음이 된 경우
			if (start == 2) {
				minCount += tempCount; // 임시 비교 횟수를 최소 비교 횟수에 더해줌
				pq.add(tempCount); // 두 카드 묶음을 합친 새로운 카드 묶음을 우선순위 큐에 저장
				tempCount = 0; // 임시 비교 횟수 다시 0으로 초기화
				start = 0; // 두 카드 묶음을 합친 횟수 초기화
			}
		}
		
		System.out.println(minCount);

	}

}