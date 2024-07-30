import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int C;
	static int[] homeArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		homeArr = new int[N];
		
		for (int i=0; i<N; i++) {
			homeArr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(homeArr);
		
		// 이분탐색 알고리즘 이용
		int start = 1;
		int end = homeArr[N-1] - homeArr[0];
		int closeWifiMaxDistance = 0;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			
			if (checkWifiInstall(mid) >= C) {
				start = mid + 1;
				closeWifiMaxDistance = mid;
			}
			else {
				end = mid - 1;
			}
		}
		
		System.out.println(closeWifiMaxDistance);
	}
	
	public static int checkWifiInstall(int distance) {
		int count = 1;
		int lastHome = homeArr[0];
		
		for (int i=1; i<N; i++) {
			if (homeArr[i] - lastHome >= distance) {
				count++;
				lastHome = homeArr[i];
			}
		}
		
		return count;
	}

}
