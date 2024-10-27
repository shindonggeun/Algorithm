import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int M;
	static Map<String, String> sitePasswordMap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		sitePasswordMap = new HashMap<>();
		
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			String[] inputSplit = input.split(" ");
			
			sitePasswordMap.put(inputSplit[0], inputSplit[1]);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<M; i++) {
			String site = br.readLine();
			
			sb.append(sitePasswordMap.get(site)).append("\n");
		}
		
		System.out.print(sb);

	}

}