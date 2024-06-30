import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int C;
	static int[] productArr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		productArr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			productArr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(productArr);
		boolean buyOk = false;
		
		for (int i=0; i<N; i++) {
			// 선택한 물건 1개로 가능한 경우
			if (productArr[i] == C) {
				buyOk = true;
				break;
			}
			
			for (int j=i+1; j<N; j++) {
				// 선택한 물건 2개의 조합으로 가능한 경우
				if (productArr[i] + productArr[j] == C) {
					buyOk = true;
					break;
				}
				
				// 선택한 물건 3개의 조합으로 가능한 경우 확인하기
				buyOk = binarySearch(i, j);
				
				if (buyOk) {
					break;
				}
			}
			
			if (buyOk) {
				break;
			}
		}
		
		System.out.println(buyOk == true ? 1 : 0);

	}
	
	public static boolean binarySearch(int i, int j) {
		int start = 0;
		int end = N-1;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			
			if (productArr[i] + productArr[j] + productArr[mid] == C) {
				if (i != mid && j != mid) {
					return true;
				}
			}
			
			if (productArr[i] + productArr[j] + productArr[mid] > C) {
				end = mid - 1;
			}
			else {
				start = mid + 1;
			}
		}
		
		return false;
	}

}