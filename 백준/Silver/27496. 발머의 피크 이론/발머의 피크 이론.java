import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 슬라이딩 윈도우 알고리즘 이용
		int sum = 0;
		int time = 0;
		for(int i=0; i<L; i++) {
			sum += arr[i];
			if(sum >= 129 && sum <= 138) {
				time++;
			}
		}
		
		for(int i=0; i+L<N; i++) {
			sum -= arr[i];
			sum += arr[i+L];
			if(sum >= 129 && sum <= 138) {
				time++;
			}
		}
		System.out.println(time);

	}

}
