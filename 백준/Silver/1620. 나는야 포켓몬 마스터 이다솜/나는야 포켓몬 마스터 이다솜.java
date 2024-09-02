import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static Map<Integer, String> numberMap;
	static Map<String, Integer> nameMap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		numberMap = new HashMap<>();
		nameMap = new HashMap<>();
		
		for (int i=1; i<=N; i++) {
			String name = br.readLine();
			numberMap.put(i, name);
			nameMap.put(name, i);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<M; i++) {
			String input = br.readLine();
			char ch = input.charAt(0);
			
			if (Character.isDigit(ch)) {
				int number = Integer.parseInt(input);
				String name = numberMap.get(number);
				
				sb.append(name);
			}
			else {
				int number = nameMap.get(input);
				
				sb.append(number);
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}