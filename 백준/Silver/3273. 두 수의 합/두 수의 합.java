import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int[] numArr;
	static int X;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		numArr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i=0; i<N; i++) {
			numArr[i] = Integer.parseInt(st.nextToken());
		}
		
		X = Integer.parseInt(br.readLine());
		
		Arrays.sort(numArr);
		
		int left = 0;
		int right = N-1;
		int count = 0;
		
		while (left < right) {
			int leftNum = numArr[left];
			int rightNum = numArr[right];
			
			if (leftNum + rightNum < X) {
				left++;
			}
			else {
				right--;
				
				if (leftNum + rightNum == X) {
					count++;
				}
			}
		}
		
		System.out.println(count);
		
	}

}