import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] carParking = new int[N+1];	// 몇번째 칸에 몇번차가 주차중인지 
		int[] carWeight = new int[M+1];	// 차량들의 무게를 저장해주는 배열
		int[] parkingPrice = new int[N+1];	// 주차 공간들의 단위 무게당 요금
		
		for(int i=1; i<=N; i++) {
			parkingPrice[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i=1; i<=M; i++) {
			carWeight[i] = Integer.parseInt(br.readLine());
		}
		
		Queue<Integer> queue = new LinkedList<>();
		int sum = 0;
		
		for(int i=0; i<2*M; i++) {
			int carEntryNum = Integer.parseInt(br.readLine());	// 해당 자동차번호
			boolean find = false;
			// 주차하는 경우
			if(carEntryNum > 0) {
				// 주차공간 번호순대로
				for(int j=1; j<=N; j++) {
					// 주차공간이 빈 경우
					if(carParking[j] == 0) {
						carParking[j] = carEntryNum;	// 주차공간에 해당 차량번호 저장(해당 차를 주차함)
						find = true;					// 주차공간 빈자리 있음
						break;	
					}
				}
				// 주차공간 빈자리 다 탐색했는데도 없는 경우
				if(!find) {
					// 큐에 집어넣음(대기중)
					queue.offer(carEntryNum);
				}
				
			}
			// 주차장 나가는 경우
			else {
				// 주차공간 번호순대로 탐색
				for(int j=1; j<=N; j++) {
					// 해당 차량번호가 주차된 주차공간인 경우
					if(carParking[j] == carEntryNum * (-1)) {
						carParking[j] = 0;	// 주차공간 빈자리로 처리
						sum += parkingPrice[j] * carWeight[carEntryNum * (-1)];
						// 큐가 비어있지 않은 경우
						if(!queue.isEmpty()) {
							carParking[j] = queue.poll();	// 차가 빠져나간 자리에 맨 앞에 있는 차를 주차한다
						}
						break;
					}
				}
			}
		}
		System.out.println(sum);

	}

}
