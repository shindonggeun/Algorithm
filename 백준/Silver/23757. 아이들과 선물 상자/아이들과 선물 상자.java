import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());	// 높은값이 우선순위 높은 것
		st = new StringTokenizer(br.readLine());
		// 선물 상자에 담겨져 있는 선물의 개수 입력받기
		for(int i=0; i<N; i++) {
			pq.add(Integer.parseInt(st.nextToken()));
		}
		
		st = new StringTokenizer(br.readLine());
		int[] wantPresent = new int[M];	// 아이들이 번호 순으로 각 아이가 원하는 선물의 개수
		for(int i=0; i<M; i++) {
			wantPresent[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean find = true;
		for(int i=0; i<M; i++) {
			int giftBox = pq.poll();
			int num = giftBox - wantPresent[i];
			
			// 선물을 다 가져가고 남은 경우
			if(num > 0) {
				pq.add(num);	// 우선순위큐에 남은 선물개수 집어넣음
			}
			// 선물을 다 가져가지 못하는 경우
			else if(num < 0){
				find = false;	// 원하는만큼 가져갈 수 없음
				break;
			}
		}
		
		if(find) {
			System.out.println(1);
		}
		else {
			System.out.println(0);
		}

	}

}
