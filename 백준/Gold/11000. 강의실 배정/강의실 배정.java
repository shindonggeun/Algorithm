import java.util.*;
import java.io.*;

public class Main {
	
	static class Time implements Comparable<Time> {
		int startTime;
		int endTime;
		
		public Time(int startTime, int endTime) {
			this.startTime = startTime;
			this.endTime = endTime;
		}
		
		@Override
		public int compareTo(Time t) {
			if(this.startTime > t.startTime) {
				return 1;	
			}
			else if(this.startTime < t.startTime) {
				return -1;
			}
			// 강의 시작시간이 같은경우 강의 종료시간을 기준으로 오름차순 정렬
			else {
				return this.endTime - t.endTime;
			}
				
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		Time[] times = new Time[N];	// 강의실을 배정받지 못한 대기중인 상태의 강의들 (강의들의 시간을 저장한 배열)
		PriorityQueue<Integer> pq = new PriorityQueue<>();	// 최소 강의실의 수를 구하기 위해 우선순위 큐 이용
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int startTime = Integer.parseInt(st.nextToken());
			int endTime = Integer.parseInt(st.nextToken());
			times[i] = new Time(startTime, endTime);
		}
		
		Arrays.sort(times);	// Time 클래스에서 compareTo() 메서드 오버라이딩한 기준으로 정렬
		
		pq.add(times[0].endTime);	// 먼저 첫 강의의 종료시간을 우선순위 큐에 집어넣음
		
		for(int i=1; i<N; i++) {
			int tempTime = pq.peek();		
			// 강의실을 배정받지 못한 강의 시작시간과 강의중인 강의의 종료시간을 비교해서 강의중인 강의가 이미 종료됐으면
			if(times[i].startTime >= tempTime) {
				pq.poll();	// 강의중인 강의 종료
			}
			
			pq.add(times[i].endTime);	// 우선순위 큐에 해당 강의의 종료시간 넣음
		}
		
		System.out.println(pq.size());	// 우선순위 큐의 사이즈가 최소 강의실의 수임
	

	}

}
