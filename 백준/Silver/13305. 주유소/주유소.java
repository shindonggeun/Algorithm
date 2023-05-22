import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// 도시의 개수 입력
		st = new StringTokenizer(br.readLine());
		long[] dist = new long[N-1];	// 거리가 1이상 1,000,000,000 이하의 자연수이므로 long타입으로 선언
		long[] oilPrice = new long[N];		// 리터당 가격은 1 이상 1,000,000,000 이하의 자연수이므로 long타입으로 선언 
		
		
		for(int i=0; i<N-1; i++) {
			dist[i] = Long.parseLong(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			oilPrice[i] = Long.parseLong(st.nextToken());
		}
		
		long sum = 0;
		long minPrice = oilPrice[0];	// 이전까지의 과정 중 주유 최소 비용
		
		for(int i=0; i<N-1; i++) {
			// 현재 주유소가 이전 주유소의 기름값보다 싼 경우 다시 minPrice값을 갱신해준다
			if(oilPrice[i] < minPrice) {
				minPrice = oilPrice[i];
			}
			
			sum += (minPrice * dist[i]);	// 총 비용 누적합
		}
		
		System.out.println(sum);

	}

}
