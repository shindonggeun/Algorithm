import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int result = N / 4;
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<result; i++) {
			sb.append("long ");
		}
		sb.append("int");
		System.out.println(sb);

	}

}