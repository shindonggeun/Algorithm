import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=0; tc<T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[] A_arr = new int[N];
			int[] B_arr = new int[M];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				A_arr[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				B_arr[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(B_arr);	// 먹히는 배열 B 오름차순 정렬 (이분탐색 이용하기 위해)
			
			int result = 0;
			
			for(int i=0; i<N; i++) {
				// 이분탐색 알고리즘 이용
				int left = 0;
				int right = M-1;
				int index = 0;	// 인덱스값이 선택된 A의 값보다 작은 B 요소의 개수임
				
				while(left <= right) {
					int mid = (left + right) / 2;
					if(B_arr[mid] < A_arr[i]) {
						left = mid + 1;
						index = mid + 1;
					}
					else {
						right = mid - 1;
					}
				}
				result += index;	// 이분탐색 끝났으면 결과인 index 값을 더해줘서 선택된 A의 값보다 작은 B 요소의 개수를 찾을 수 있게 됨
			}
			sb.append(result).append("\n");
		}
		System.out.print(sb);

	}

}
