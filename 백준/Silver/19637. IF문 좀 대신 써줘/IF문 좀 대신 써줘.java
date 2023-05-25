import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		String[] titleArr = new String[N];
		int[] powerArr = new int[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			titleArr[i] = st.nextToken();
			powerArr[i] = Integer.parseInt(st.nextToken());	// 전투력 입력받는 것 오름차순으로 주어짐
		}
		
		// 전투력 입력받는 것 오름차순으로 주어졌으므로 정렬과정을 거치지 않고 이분탐색을 시작해도 무방
		
		for(int i=0; i<M; i++) {
			int inputPower = Integer.parseInt(br.readLine());
			// 이분탐색 알고리즘 이용
			int left = 0;
			int right = N-1;
			
			while(left <= right) {
				int mid = (left + right) / 2;
				if(powerArr[mid] < inputPower) {
					left = mid + 1;
				}
				else {
					right = mid - 1;
				}
			}
			sb.append(titleArr[left]).append("\n");
		}
		System.out.print(sb);
		

	}

}
