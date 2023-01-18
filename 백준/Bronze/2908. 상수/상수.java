import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String str_a = st.nextToken();
		String str_b = st.nextToken();
		String reverse_a = "";
		String reverse_b = "";
		
		for(int i=str_a.length() - 1; i>=0; i--) {
			reverse_a+=str_a.charAt(i);
		}
		
		for(int i=str_b.length() - 1; i>=0; i--) {
			reverse_b+=str_b.charAt(i);
		}
		
		int result_a = Integer.parseInt(reverse_a);
		int result_b = Integer.parseInt(reverse_b);
		
		if(result_a > result_b) {
			System.out.println(result_a);
		}
		else if(result_a < result_b) {
			System.out.println(result_b);
		}
		
    }
}