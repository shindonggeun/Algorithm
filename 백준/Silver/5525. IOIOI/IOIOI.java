import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(br.readLine());
		
		char[] input = br.readLine().toCharArray();
		
		int result = 0;
		int count = 0;
		
		for(int i=1; i<M-1; i++) {
			if(input[i-1] == 'I' && input[i] == 'O' && input[i+1] == 'I') {
				count++;
				if(count == N) {
					count--;
					result++;
				}
	            i++;
			}
			else {
				count = 0;
			}
		}
		
		System.out.println(result);
	}

}