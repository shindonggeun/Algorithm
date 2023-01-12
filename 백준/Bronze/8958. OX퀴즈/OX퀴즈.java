import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int num = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<num; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			int count = 0;
			int result = 0;
			for(int j=0; j<str.length(); j++) {
				if(str.charAt(j) == 'X') {
					count = 0;
				} else {
					count++;
					result+=count;
				}				
			}
			sb.append(result).append("\n");
		}
		System.out.println(sb);
    }
}