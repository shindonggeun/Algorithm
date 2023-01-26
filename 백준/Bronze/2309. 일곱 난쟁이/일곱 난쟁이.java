import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[] arr = new int[9];
		int result = 0;
		int spy_a = 0;
		int spy_b = 0;
		
		for(int i=0; i<9; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
			result+=arr[i];
		}

		Arrays.sort(arr);	// 오름차순 정렬
		
		// 브루트 포스
		for(int i=0; i<arr.length-1; i++) {
			for(int j=i+1; j<arr.length; j++) {
				if(result - arr[i] - arr[j] == 100) {
					spy_a = i;
					spy_b = j;
					break;
				}
			}
		}
		
		for(int i=0; i<arr.length; i++) {
			if(spy_a == i || spy_b == i) {
				continue;
			}
			System.out.println(arr[i]);
		}
		
	}

}