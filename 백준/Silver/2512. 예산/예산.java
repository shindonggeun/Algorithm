import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int[] budgetArr;
	static int M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		budgetArr = new int[N];
		int maxBudget = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			budgetArr[i] = Integer.parseInt(st.nextToken());
			maxBudget = Math.max(maxBudget, budgetArr[i]);
		}
		
		M = Integer.parseInt(br.readLine());
		
		int resultBudget = binarySearch(0, maxBudget);
		System.out.println(resultBudget);
		
	}
	
	public static int binarySearch(int low, int high) {
		int result = 0;
		
		while(low <= high) {
			int mid = (low + high) / 2;
			int sum = 0;
			
			for(int budget: budgetArr) {
				sum += Math.min(budget, mid);
			}
			
			if(sum > M) {
				high = mid - 1;
			}
			else {
				low = mid + 1;
				result = mid;
			}
			
		}
		
		return result;
	}

}
