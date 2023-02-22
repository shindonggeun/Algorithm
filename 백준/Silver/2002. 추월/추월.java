import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		Map<String, Integer> map = new HashMap<>();	// key: 차량번호, value: 원래 차량 들어온 순서
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String carNumber = st.nextToken();
			map.put(carNumber, i);
		}
		
		String[] input = new String[N]; 
		// 터널 내부에 들어온 차량들 순서대로 입력받기
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			input[i] = st.nextToken();
		}
		
		int count = 0;
		// 브루트포스 알고리즘 이용
		for(int i=0; i<N-1; i++) {
			for(int j=i+1; j<N; j++) {
				// 차량이 추월한 경우
				// 더이상 내부반복문 돌려서 비교할 필요없이 추월한 차량 카운트 하고 내부 반복문 빠져나옴
				if(map.get(input[i]) > map.get(input[j])) {
					count++;
					break;	 
				}
			}
		}
		System.out.println(count);

	}

}