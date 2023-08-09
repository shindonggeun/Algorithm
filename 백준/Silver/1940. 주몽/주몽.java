import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());	
		
		int[] num = new int[N];			// 갑옷을 만드는데 필요한 재료들(고유번호)
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		int count = 0;
		// 갑옷 2개만 추출이므로 브루트포스 사용
		for(int i=0; i<N-1; i++) {
			for(int j=i+1; j<N; j++) {
				// 갑옷의 고유번호 합쳐서 M이면 count 증가
				if(num[i] + num[j] == M) {
					count++;
				}
			}
		}
		System.out.println(count);
		

	}

}