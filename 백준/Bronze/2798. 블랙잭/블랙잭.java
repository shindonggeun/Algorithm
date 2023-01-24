import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] card = new int[N];
		
		for(int i=0; i<N; i++) {
			card[i] = Integer.parseInt(st.nextToken());
		}
		
		int answer = search(N, M, card);
		System.out.println(answer);
		
	}
	
	public static int search(int N, int M, int[] card) {
		int result = 0;	// 이전 합
		
		// 3장 뽑기
		for(int i=0; i<N-2; i++) {
			for(int j=i+1; j<N-1; j++) {
				for(int k=j+1; k<N; k++) {
					int sum = card[i] + card[j] + card[k];
					
					if(M == sum) {
						return sum;
					}
					// 세 카드의 합이 이전 합보다 크면서 M보다 작은 경우 result를 세 카드의 합으로 초기화 
					if(result < sum && sum < M) {
						result = sum;
					}
				}
			}
		}
		
		// 모든 순회 마친경우 result 리턴
		return result;
	}

}