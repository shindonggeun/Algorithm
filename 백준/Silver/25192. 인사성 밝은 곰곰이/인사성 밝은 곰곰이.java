import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static Set<String> userSet;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		userSet = new HashSet<>();
		
		int count = 0;
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			
			if (input.equals("ENTER")) {
				userSet.clear();
				continue;
			}
			
			if (!userSet.contains(input)) {
				userSet.add(input);
				count++;
			}
		}
		
		System.out.println(count);
	}

}