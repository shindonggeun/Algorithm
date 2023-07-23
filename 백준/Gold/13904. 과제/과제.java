import java.util.*;
import java.io.*;

public class Main {
	
	static class HomeWork {
		
		int d, w;
		
		public HomeWork(int d, int w) {
			this.d = d;
			this.w = w;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		List<HomeWork> list = new ArrayList<>();
		int maxDay = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());	// 과제 마감일까지 남은 일수
			int w = Integer.parseInt(st.nextToken());	// 과제의 점수
			
			list.add(new HomeWork(d, w));
			maxDay = Math.max(maxDay, d);	// 과제 마감일까지 남은 일수 가장 높은 것 갱신
		}
		
		int sum = 0;	// 과제 점수 합
		
		// 과제 마감일 가장 오래걸리는 것부터 역순으로 
		for(int i=maxDay; i>=1; i--) {
			HomeWork result = new HomeWork(0, 0);
			
			// 리스트에 저장된 과제들 뽑아내기
			for(HomeWork hw: list) {
				if(hw.d >= i) {
					// 과제 점수가 높은 경우
					if(result.w < hw.w) {
						result = hw;	// 과제 갱신
					}
				}
			}
			
			sum += result.w;	// 해당 과제 점수 더해준 뒤
			list.remove(result);	// 리스트에 저장된 해당 과제 삭제 (과제 끝냄)
		}
		
		System.out.println(sum);

	}

}
