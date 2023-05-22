import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long S = Long.parseLong(st.nextToken());
		
		long sum = 0;
		int count = 0;
		// 무한반복 
		for(int i=1; ; i++) {
			if(S < sum) {
				break;
			}
			sum += i;
			count++;
		}
		System.out.println(count-1);

	}

}
