import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// 거인의 나라 인구수
		int centiHeight = Integer.parseInt(st.nextToken());	// 센티의 키
		int T = Integer.parseInt(st.nextToken());	// 마법의 뿅망치 횟수 제한
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());	// 키가 높은애들이 우선순위 높음
		for(int i=0; i<N; i++) {
			pq.add(Integer.parseInt(br.readLine()));
		}
		
		int count = 0;	// 뿅망치 최소 사용 횟수
		// 뿅망치 쓰기
		for(int i=0; i<T; i++) {
			int height = pq.poll();	// 거인의 나라에서 최장신 뽑아내기
			// 최장신의 키가 센티의 키 보다 작은 경우
			if((height < centiHeight)) {
				pq.add(height);	
				break;
			}
			else {
				count++;
				// 뿅망치 사용해서 키가 0이 된 경우(해당 키가 1인 경우 더 줄어들수가 없음)
				if(height/2 == 0) {
					pq.add(1);	
					break;	// 해당 반복문 빠져나옴
				}
				else {
					pq.add(height/2);	// 뿅망치 사용해서 키 절반으로 줄어들게한 뒤 다시 우선순위 큐에 집어넣음
				}
			}
		}
		
		if(pq.peek() < centiHeight) {
			System.out.println("YES");
			System.out.println(count);
		}
		else {
			System.out.println("NO");
			System.out.println(pq.poll());
		}
	}

}
