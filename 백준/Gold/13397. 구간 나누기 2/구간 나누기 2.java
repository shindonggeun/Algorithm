import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static int[] numArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		numArr = new int[N];
		int maxScore = Integer.MIN_VALUE;
		int minScore = Integer.MAX_VALUE;
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			numArr[i] = Integer.parseInt(st.nextToken());
			maxScore = Math.max(maxScore, numArr[i]);
			minScore = Math.min(minScore, numArr[i]);
		}
		
		int start = 0;
		int end = maxScore - minScore;
		int result = end;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			
			if (divideCheck(mid)) {
				end = mid - 1;
				result = mid;
			}
			else {
				start = mid + 1;
			}
		}
		
		System.out.println(result);
	}
	
	public static boolean divideCheck(int maxDiff) {
		int divideSectionCount = 1;
		int maxScore = numArr[0];
		int minScore = numArr[0];
		
		for (int i=1; i<N; i++) {
			maxScore = Math.max(maxScore, numArr[i]);
			minScore = Math.min(minScore, numArr[i]);
			
			if (maxScore - minScore > maxDiff) {
				divideSectionCount++;
				maxScore = numArr[i];
				minScore = numArr[i];
			}
			
			if (divideSectionCount > M) {
				return false;
			}
		}
		
		return true;
	}

}