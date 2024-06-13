import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static char[] S;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		String input = "";
		for (int i=0; i<N; i++) {
			input += br.readLine();
		}
		
		S = input.toCharArray();
		
		StringBuilder T = new StringBuilder();
		int left = 0;
		int right = input.length() - 1;
		
		while (left <= right) {
			boolean leftSmaller = false;
			for (int i=0; left+i<=right; i++) {
				if (S[left + i] < S[right - i]) {
					leftSmaller = true;
					break;
				}
				else if (S[left + i] > S[right - i]) {
					leftSmaller = false;
					break;
				}
			}
			
			if (leftSmaller) {
				T.append(S[left++]);
			}
			else {
				T.append(S[right--]);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<T.length(); i++) {
			sb.append(T.charAt(i));
			if ((i + 1) % 80 == 0) {
				sb.append("\n");
			}
		}
		
		System.out.print(sb);

	}

}