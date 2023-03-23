import java.util.*;
import java.io.*;

public class Main {
	
	static class Hour {
		int start = 0;	// 회의 시작 시간
		int end = 0;	// 회의 끝나는 시간
		
		public Hour(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		List<Hour> list = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			list.add(new Hour(start, end));
		}
		
		// 회의 끝나는 시간 순으로 정렬해주기
		Collections.sort(list, new Comparator<Hour>() {

			@Override
			public int compare(Hour h1, Hour h2) {
				// 회의 끝나는 시간이 같은 경우
				if(h1.end == h2.end) {
					return h1.start - h2.start;	// 회의 시작 시간 순으로 오름차순 정렬
				}
				return h1.end - h2.end;	// 회의 끝나는 시간순으로 오름차순 정렬
			}
		});
		int count = 0;			// 최대 사용할 수 있는 회의실의 개수
		int prevEndHour = 0;	// 이전 회의 끝나는 시간
		
		for(int i=0; i<list.size(); i++) {
			// 이전 회의의 끝나는 시간이 회의 시작 시간보다 작거나 같은 경우
			if(prevEndHour <= list.get(i).start) {
				prevEndHour = list.get(i).end;	// 회의 끝나는 시간 갱신해줌
				count++;
			}
		}
		System.out.println(count);

	}

}
