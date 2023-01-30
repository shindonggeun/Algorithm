import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		long C = Long.parseLong(st.nextToken());
		
		long result_A = A;
		long result_C = 0;
		int count = 0;
		
		
		for(int i=1; i<=2100000001;i++) {
			result_A+=B;
			result_C = C * i;

			if(result_A < result_C) {
				count = i;
				break;
			}
			count = -1;
		}
		
		System.out.println(count);
	}

}