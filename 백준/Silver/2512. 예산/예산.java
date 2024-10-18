import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int M;
	static int[] budgetArr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		budgetArr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			budgetArr[i] = Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		
		Arrays.sort(budgetArr);
		
		int start = 0;
		int end = budgetArr[N-1];
		int maxBudget = 0;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			
			if (checkBudget(mid)) {
				maxBudget = mid;
				start = mid + 1;
			}
			else {
				end = mid - 1;
			}
		}
		
		System.out.println(maxBudget);
	}
	
	public static boolean checkBudget(int settingBudget) {
		int sum = 0;
		
		for (int i=0; i<N; i++) {
			sum += Math.min(budgetArr[i], settingBudget);
		}
		
		return sum <= M;
	}

}