import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		List<Integer> list = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int coin = Integer.parseInt(st.nextToken());
			list.add(coin);
		}
		Collections.sort(list, Collections.reverseOrder());	// 해당 동전들 저장한값 내림차순 정렬
		
		int count = 0;	// 모든 동전 사용한 개수
		for(int coin: list) {
			// 입력값 K가 해당 동전보다 작거나 같은 경우
			if(coin <= K) {
				count += (K / coin);	// 입력값 K에서 해당 동전을 나눈값이 해당 동전을 이용한 개수임
				K = K % coin;			
			}
		}
		System.out.println(count);

	}

}